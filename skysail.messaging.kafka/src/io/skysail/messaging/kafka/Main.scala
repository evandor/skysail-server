package io.skysail.messaging.kafka

import java.util
import org.apache.kafka.clients.consumer._

object Main {
  def main(args: Array[String]): Unit = {
    println("Hello from main of class")

    import java.io.PrintWriter
    val caWriter: PrintWriter = new PrintWriter("/tmp/ca.pem", "UTF-8")
    val ca: String = System.getenv("CLOUDKARAFKA_CA")
    caWriter.println(ca)
    caWriter.close()

    import java.io.PrintWriter
    val certWriter: PrintWriter = new PrintWriter("/tmp/cert.pem", "UTF-8")
    val cert: String = System.getenv("CLOUDKARAFKA_CERT")
    certWriter.println(cert)
    certWriter.close()

    import java.io.PrintWriter
    val keyWriter: PrintWriter = new PrintWriter("/tmp/key.pem", "UTF-8")
    val privateKey: String = System.getenv("CLOUDKARAFKA_PRIVATE_KEY")
    keyWriter.println(privateKey)
    keyWriter.close()

    val r = Runtime.getRuntime
    var p = r.exec("openssl pkcs12 -export -password pass:test1234 -out /tmp/store.pkcs12 -inkey /tmp/key.pem -certfile /tmp/ca.pem -in /tmp/cert.pem -caname 'CA Root' -name client")
    p.waitFor

    p = r.exec("keytool -importkeystore -noprompt -srckeystore /tmp/store.pkcs12 -destkeystore /tmp/keystore.jks -srcstoretype pkcs12 -srcstorepass test1234 -srckeypass test1234 -destkeypass test1234 -deststorepass test1234 -alias client")
    p.waitFor

    p = r.exec("keytool -noprompt -keystore /tmp/truststore.jks -alias CARoot -import -file /tmp/ca.pem -storepass test1234")
    p.waitFor

    val brokers = System.getenv("CLOUDKARAFKA_BROKERS")
    val topicPrefix = System.getenv("CLOUDKARAFKA_TOPIC_PREFIX")

    import java.util
    val props: java.util.Properties = new java.util.Properties()
    props.put("bootstrap.servers", brokers)
    props.put("group.id", "test")
    props.put("enable.auto.commit", "true")
    props.put("auto.commit.interval.ms", "1000")
    props.put("session.timeout.ms", "30000")
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("security.protocol", "SSL")
    props.put("ssl.truststore.location", "/tmp/truststore.jks")
    props.put("ssl.truststore.password", "test1234")
    props.put("ssl.keystore.location", "/tmp/keystore.jks")
    props.put("ssl.keystore.password", "test1234")
    props.put("ssl.keypassword", "test1234")

    val consumer = new org.apache.kafka.clients.consumer.KafkaConsumer(props)
    consumer.subscribe(util.Arrays.asList(topicPrefix + "default"))
    while ( {
      true
    }) {
      val records = consumer.poll(100)
      import scala.collection.JavaConversions._
      for (record <- records) {
        println(s"offset = ${record.offset}, key = ${record.key}, value = ${record.value}")
      }
    }

  }
}
