package io.skysail.server.app.bb.goals;

import org.restlet.resource.ResourceException;

import io.skysail.api.responses.SkysailResponse;
import io.skysail.server.app.bb.BodyboosterApplication;
import io.skysail.server.app.bb.Goal;
import io.skysail.server.restlet.resources.PutEntityServerResource;

public class PutGoalResource extends PutEntityServerResource<Goal> {

	private BodyboosterApplication app;

	@Override
	protected void doInit() throws ResourceException {
		super.doInit();
		app = (BodyboosterApplication)getApplication();
	}

	@Override
	public Goal getEntity() {
		return app.getRepository().getById(getAttribute("id"));
	}

	@Override
	public SkysailResponse<Goal> updateEntity(Goal entity) {
		Object update = app.getRepository().update(getAttribute("id"), entity);
		return new SkysailResponse<>();
	}
	
	@Override
	public String redirectTo() {
	    return super.redirectTo(GoalsResource.class);
	}


}
