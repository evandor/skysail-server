package io.skysail.server.app.bb.goals;

import org.restlet.resource.ResourceException;

import io.skysail.api.responses.SkysailResponse;
import io.skysail.server.app.bb.BodyboosterApplication;
import io.skysail.server.app.bb.Goal;
import io.skysail.server.restlet.resources.PostEntityServerResource;

public class PostGoalResource extends PostEntityServerResource<Goal> {

	private BodyboosterApplication app;

	@Override
	protected void doInit() throws ResourceException {
		super.doInit();
		app = (BodyboosterApplication)getApplication();
	}
	
	@Override
	public Goal createEntityTemplate() {
		return new Goal();
	}

	@Override
	public SkysailResponse<Goal> addEntity(Goal entity) {
		String id = app.getRepository().save(entity).toString();
		entity.setId(id);
		return new SkysailResponse<>(entity);
	}

//	@Override
//	public String redirectTo() {
//	    return super.redirectTo(GoalsResource.class);
//	}
}