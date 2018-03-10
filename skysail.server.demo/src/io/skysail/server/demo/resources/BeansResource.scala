package io.skysail.server.demo.resources

import akka.stream.ActorMaterializer
import io.skysail.domain.resources.EntityResource
import io.skysail.domain.{RequestEvent, Transformer}
import io.skysail.server.demo.DemoApplication
import io.skysail.server.demo.domain.SpringBeanList
import org.apache.http.HttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.{ClientProtocolException, ResponseHandler}
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils
import org.json4s.{DefaultFormats, jackson}

class BeansResource extends EntityResource[DemoApplication, SpringBeanList]{

  private val httpclient = HttpClients.createDefault

  override def getEntity(re: RequestEvent): Option[SpringBeanList] = {

    implicit val formats = DefaultFormats
    implicit val serialization = jackson.Serialization
    implicit val system = actorContext.system
    implicit val materializer = ActorMaterializer()

    val res: String = get("http://localhost:8080/einschreibung/actuator/beans")
    val res2 = res.substring(1,res.length-1)
//    val v = akka.http.scaladsl.model.HttpEntity.Strict(ContentTypes.`application/json`, ByteString(res)).asInstanceOf[ResponseEntity]
//    val x = Unmarshal(v)
//   // val u = x.to[SpringBeanList]
//
    val r:SpringBeanList = Transformer.jsonStringToBean[SpringBeanList](res2)
    None
  }

  def get(path: String) /*(implicit system: ActorSystem = ActorSystem())*/ = {
    //    implicit val materializer = ActorMaterializer()
    //
    //    val source = Source.single(HttpRequest(uri = Uri(path = Path("/_cat/indices?format=json"))))
    //    val flow = Http().outgoingConnectionHttps("localhost", 9200).mapAsync(1) { r =>
    //      Unmarshal(r.entity).to[EsIndex]
    //    }
    //
    //    source.via(flow).runWith(Sink.head)

    val httpget = new HttpGet(path)
    val responseHandler = new ResponseHandler[String]() {
      override def handleResponse(response: HttpResponse): String = {
        val status = response.getStatusLine.getStatusCode
        if (status >= 200 && status < 300) {
          val entity = response.getEntity
          if (entity != null) EntityUtils.toString(entity)
          else null
        } else throw new ClientProtocolException("Unexpected response status: " + status)
      }
    }
    httpclient.execute(httpget, responseHandler)
  }

}
