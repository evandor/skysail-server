//package io.skysail.server.app.oil.test;
//
//import static org.hamcrest.Matchers.is;
//import static org.hamcrest.Matchers.notNullValue;
//import static org.junit.Assert.assertThat;
//
//import org.junit.Ignore;
//import org.junit.Test;
//
//import io.skysail.api.responses.SkysailResponse;
//import io.skysail.server.app.oil.OpenItem;
//
//@Ignore
//public class PostItemResourceTest extends AbstractItemResourceTest {
//
//	@Test
//	public void valid_form_data_yields_new_entity() {
//		form.add("title", "aTitle");
//		SkysailResponse<OpenItem> result = postItemResource.post(form, HTML_VARIANT);
//		assertListResult(postItemResource, result, "aTitle");
//		assertThat(result.getEntity().getTopic(), is(notNullValue()));
//	}
//
//}
