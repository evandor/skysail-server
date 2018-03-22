package io.skysail.server.demo

import org.junit.Assert.assertTrue

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;

object IntegrationTest {
  @BeforeClass
  def setup(): Unit = {
    println("waiting for service  for 7500 ms!...")
    Thread.sleep(7500)
    println("waited for service - done!")
  }
}

class IntegrationTest {

  private val log = LoggerFactory.getLogger(classOf[IntegrationTest])

  val context = FrameworkUtil.getBundle(this.getClass()).getBundleContext()

  val thisBundle = FrameworkUtil.getBundle(this.getClass())

  val httpclient = HttpClients.createDefault()

  //    @Rule
  //    val thrown = ExpectedException.none()
  //
  //    @Rule
  //    val watcher = new TestWatcher() {
  //        override def starting(description: Description) = {
  //            log info ""
  //            log info "----------------------------------------------"
  //            log info s"running test '${description.getMethodName()}'"
  //            log info "----------------------------------------------"
  //            log info ""
  //        }
  //    }

  @Before
  def init() {
    //getService(io.skysail.server.app.ApplicationProvider.class);
    println("hier")
  }

  @Test
  def root_resources_returns_info_message_if_no_apps_have_been_deployed_yet() {
    val responseBody = get("http://localhost:8001/_root");
    assertTrue(responseBody.contains("powered by skysail-server"))
  }

  private def get(path: String): String = {
    val httpget = new HttpGet(path);
    val responseHandler = new ResponseHandler[String]() {

      override def handleResponse(response: HttpResponse): String = {
        val status = response.getStatusLine().getStatusCode();
        if (status >= 200 && status < 300) {
          val entity = response.getEntity();
          return if (entity != null) EntityUtils.toString(entity) else null
        } else {
          throw new ClientProtocolException("Unexpected response status: " + status);
        }
      }

    };
    val responseBody = httpclient.execute(httpget, responseHandler);
    System.out.println("--------------------------------------------------------");
    System.out.println(responseBody);
    System.out.println("--------------------------------------------------------");
    return responseBody;
  }

}
