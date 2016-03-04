//package io.skysail.server.app.oil.test;
//
//import static org.hamcrest.Matchers.is;
//import static org.junit.Assert.assertThat;
//
//import java.util.HashMap;
//
//import org.apache.shiro.subject.SimplePrincipalMap;
//import org.junit.Before;
//import org.mockito.Mockito;
//import org.mockito.Spy;
//import org.restlet.data.Status;
//
//import io.skysail.api.responses.SkysailResponse;
//import io.skysail.server.app.oil.OilApplication;
//import io.skysail.server.app.oil.OilRepository;
//import io.skysail.server.app.oil.OpenItem;
//import io.skysail.server.app.oil.PostItemResource;
//import io.skysail.server.restlet.resources.SkysailServerResource;
//import io.skysail.server.testsupport.ResourceTestBase;
//
//public abstract class AbstractItemResourceTest extends ResourceTestBase {
//
//    @Spy
//    protected PostItemResource postItemResource;
//
////    @Spy
////    protected PutListResource putListResource;
//
////    @Spy
////    protected AreasResource listsResource;
//
////    @Spy
////    protected ListResource listResource;
//
//    @Spy
//    private OilApplication application;
//
//    protected OilRepository repo;
//
//    @Before
//    public void setUp() throws Exception {
//        super.setUpFixture();
//
//        super.setUpApplication(application);//Mockito.mock(TodoApplication.class));
////        super.setUpResource(listResource);
//        //super.setUpResource(listsResource);
////        super.setUpResource(putListResource);
//        super.setUpResource(postItemResource);
//        setUpRepository(new OilRepository());
//        setUpSubject("admin");
//        
////        application.setRe(testDb);
//
//        //new UniquePerOwnerValidator().setDbService(testDb);
//    }
//
//    protected void assertListResult(SkysailServerResource<?> resource, SkysailResponse<OpenItem> result, String title) {
//    	OpenItem entity = result.getEntity();
//        assertThat(responses.get(resource.getClass().getName()).getStatus(),is(Status.SUCCESS_CREATED));
//        assertThat(entity.getTitle(),is(title));
//    }
//
//    public void setUpRepository(OilRepository rep) {
//        repo = rep;
//        repo.setDbService(testDb);
//        repo.activate();
//       // ((BodyboosterApplication)application).setRepository(repo);
//        Mockito.when(((OilApplication)application).getRepository()).thenReturn(repo);
//
//    }
//
//    public void setUpSubject(String owner) {
//        Mockito.when(subjectUnderTest.getPrincipal()).thenReturn(owner);
//        Mockito.when(subjectUnderTest.getPrincipals()).thenReturn(new SimplePrincipalMap(new HashMap<>()));
//        setSubject(subjectUnderTest);
//    }
//
////    protected TodoList createList() {
////        TodoList aList = new TodoList();
////        aList.setName("list_" + randomString());
////        SkysailResponse<TodoList> post = postListresource.post(aList,JSON_VARIANT);
////        getAttributes().clear();
////
////        return post.getEntity();
////    }
//
//    protected void init(SkysailServerResource<?> resource) {
//        resource.init(null, request, responses.get(resource.getClass().getName()));
//    }
//
//    protected void setAttributes(String name, String id) {
//        getAttributes().clear();
//        getAttributes().put(name, id);
//    }
//
//}
