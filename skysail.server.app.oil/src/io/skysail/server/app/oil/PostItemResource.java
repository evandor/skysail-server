package io.skysail.server.app.oil;

import io.skysail.api.responses.SkysailResponse;
import io.skysail.server.app.SkysailApplication;
import io.skysail.server.restlet.resources.PostEntityServerResource;

public class PostItemResource extends PostEntityServerResource<OpenItem> {

	@Override
	public OpenItem createEntityTemplate() {
		return new OpenItem();
	}

	@Override
	public SkysailResponse<OpenItem> addEntity(OpenItem entity) {
		Topic topic = new Topic("firstTopic");
		entity.setTopic(topic);
		String id = ((SkysailApplication) getApplication()).getRepository().save(entity).toString();
		entity.setId(id);
		return new SkysailResponse<>(entity);
	}
}
