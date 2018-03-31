package io.skysail.server.demo.repositories

import java.util

import io.skysail.db.orientdb.repositories.HttpRepository
import io.skysail.domain.model.ApplicationModel
import io.skysail.server.demo.domain.Monitor2
import org.apache.http.NameValuePair
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.{HttpDelete, HttpEntityEnclosingRequestBase, HttpPost, HttpPut}
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.message.BasicNameValuePair
import org.json4s._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.read

class MonitorRepository(cls: Class[Monitor2], appModel: ApplicationModel) extends HttpRepository(cls, appModel) {

  implicit val formats: DefaultFormats.type = DefaultFormats
  implicit val serialization: Serialization.type = jackson.Serialization

  private val baseUrl = "http://localhost:8181/demo/monitors"

  override def find(): List[Monitor2] = {
    val result = scala.io.Source.fromURL(baseUrl).mkString
    read[List[Monitor2]](result)
  }

  override def find(id: String): Option[Monitor2] = {
    val url = s"$baseUrl/$id"
    val result = scala.io.Source.fromURL(url).mkString
    Some(read[Monitor2](result))
  }

  override def save(entity: Any): String = {
    val e =  entity.asInstanceOf[Monitor2]

    val request: HttpEntityEnclosingRequestBase = e.id match {
      case Some(s) => new HttpPut(s"$baseUrl/${s}/")
      case None => new HttpPost(s"$baseUrl/")
    }
    request.addHeader("appid","YahooDemo")
    request.addHeader("query","umbrella")
    request.addHeader("results","10")

    val client = new DefaultHttpClient
    val params = client.getParams
    params.setParameter("foo", "bar")

    val nameValuePairs = new util.ArrayList[NameValuePair](1)
    //nameValuePairs.add(new BasicNameValuePair("id", e.id.get));
    nameValuePairs.add(new BasicNameValuePair("name", e.name));
    nameValuePairs.add(new BasicNameValuePair("url", e.url));
    request.setEntity(new UrlEncodedFormEntity(nameValuePairs));

    // send the post request
    val response = client.execute(request)
    println("--- HEADERS ---")
    response.getAllHeaders.foreach(arg => println(arg))

    ""
  }

  override def delete(id: String): Boolean = {
    val delRequest = new HttpDelete(s"$baseUrl/${id}/")
    val client = new DefaultHttpClient
    val response = client.execute(delRequest)
    println("--- HEADERS ---")
    response.getAllHeaders.foreach(arg => println(arg))
    true
  }
}
