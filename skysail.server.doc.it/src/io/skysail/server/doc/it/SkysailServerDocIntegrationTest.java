package io.skysail.server.doc.it;

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

import static org.junit.Assert.assertTrue;

public class SkysailServerDocIntegrationTest {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private final BundleContext context = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
    protected Bundle thisBundle = FrameworkUtil.getBundle(this.getClass());

    private CloseableHttpClient httpclient = HttpClients.createDefault();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Rule
    public TestRule watcher = new TestWatcher() {
        @Override
        protected void starting(Description description) {
            log.info("");
            log.info("--------------------------------------------");
            log.info("{}running test '{}'", "TEST", description.getMethodName());
            log.info("--------------------------------------------");
            log.info("");
        }
    };

    @BeforeClass
    public static void setup() throws InterruptedException {
        System.out.println("wating for service  for 5000 ms...");
        Thread.currentThread().sleep(5000);
        System.out.println("waited for service - done");
    }

    @Before
    public void init() throws InterruptedException {
        getService(io.skysail.server.app.ApplicationProvider.class);
    }

    <T> T getService(Class<T> clazz) throws InterruptedException {
        ServiceTracker<T, T> st = new ServiceTracker<>(context, clazz, null);
        st.open();
        return st.waitForService(3000);
    }


    @Test
    public void metadoc_resources_returns_meta_documentation() throws Exception {
        String responseBody = get("http://localhost:8000/doc/v1/meta.html");
        assertTrue(responseBody.contains("About this document"));
    }

//    @Test
//    //@Ignore // maybe test https://github.com/dpishchukhin/org.knowhowlab.osgi.testing
//    public void stopping_and_starting_server_bundle_still_serves_root_resource() throws Exception {
//        stopAndStartBundle("skysail.server");
//        Thread.sleep(2000);
//        String responseBody = get("http://localhost:7999/root");
//        assertTrue(responseBody.contains("you are seeing this as no applications have been deployed yet"));
//    }

    private void stopAndStartBundle(String symbolicName) throws BundleException {
        Bundle[] bundles = FrameworkUtil.getBundle(this.getClass()).getBundleContext().getBundles();
        Bundle bundle = Arrays.stream(bundles)
                .filter(b -> symbolicName.equals(b.getSymbolicName()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException());
        bundle.stop();
        bundle.start();
    }

    private String get(String path) throws ClientProtocolException, IOException {
        HttpGet httpget = new HttpGet(path);
        ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

            @Override
            public String handleResponse(
                    HttpResponse response) throws ClientProtocolException, IOException {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            }

        };
        String responseBody = httpclient.execute(httpget, responseHandler);
//        System.out.println("--------------------------------------------------------");
//        System.out.println(responseBody);
//        System.out.println("--------------------------------------------------------");
        return responseBody;
    }

}
