package io.skysail.server.demo.it

import org.junit.Assert.assertTrue
import org.apache.http.HttpEntity
import org.apache.http.HttpResponse
import org.apache.http.client.ClientProtocolException
import org.apache.http.client.ResponseHandler
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils
import org.junit.Ignore
import org.junit.Test
import org.osgi.framework.FrameworkUtil
import org.slf4j.LoggerFactory
import org.apache.http.client.methods.HttpPost
import org.apache.http.message.BasicNameValuePair
import org.apache.http.client.entity.UrlEncodedFormEntity
import scala.collection.JavaConverters._
import io.skysail.server.demo.it.IntegrationTestBase



class IntegrationTest extends IntegrationTestBase {

  private val log = LoggerFactory.getLogger(classOf[IntegrationTest])

  val context = FrameworkUtil.getBundle(this.getClass()).getBundleContext()
  val thisBundle = FrameworkUtil.getBundle(this.getClass())
  val httpclient = HttpClients.createDefault()

//  val _thrown = ExpectedException.none()
//
//  val _watcher = new TestWatcher() {
//    override def starting(description: Description) = {
//      log info ""
//      log info "----------------------------------------------"     
//      log info s"running test '${description.getMethodName()}'"
//      log info "----------------------------------------------"
//      log info ""
//    }
//  }
//
//  @Rule
//  def thrown = _thrown
//
//  @Rule
//  def watcher = _watcher
//
//  @Before
//  def init() {
//    //getService(io.skysail.server.app.ApplicationProvider.class);
//    println("hier")
//  }

  @Test
  def root_resources_returns_info_message() {
    val responseBody = get("http://localhost:8001/_root");
    assertTrue(responseBody.contains("powered by skysail-server"))
  }

  @Test
  def comment1s_resources_returns_200() {
    val responseBody = get("http://localhost:8001/demo/v1/comment1s");
    assertTrue(responseBody.contains("all entities"))
  }

  @Test
  def postEntity_And_ReadList() {
    val nvps = List(new BasicNameValuePair("comment", "a comment")).asJava
    val entity = new UrlEncodedFormEntity(nvps)
    val responseBody = post("http://localhost:8001/demo/v1/comment1s/", entity);
    assertTrue(responseBody.contains("a comment"))
    assertTrue(responseBody.contains("all entities"))
  }

  private def get(path: String): String = {
    val httpget = new HttpGet(path);
    val responseBody = httpclient.execute(httpget, responseHandler);
    System.out.println("--------------------------------------------------------");
    System.out.println(responseBody);
    System.out.println("--------------------------------------------------------");
    return responseBody;
  }

  private def post(path: String, entity: HttpEntity): String = {
    val httpPost = new HttpPost(path);
    httpPost.setEntity(entity)
    httpclient.execute(httpPost, responseHandler())
  }

  private def responseHandler() = {
    new ResponseHandler[String]() {
      override def handleResponse(response: HttpResponse): String = {
        val status = response.getStatusLine().getStatusCode();
        if (status >= 200 && status < 300) {
          val entity = response.getEntity();
          return if (entity != null) EntityUtils.toString(entity) else null
        } else {
          throw new ClientProtocolException("Unexpected response status: " + status);
        }
      }
    }
  }

}
