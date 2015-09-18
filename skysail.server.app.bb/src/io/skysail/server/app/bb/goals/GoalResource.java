package io.skysail.server.app.bb.goals;

import org.restlet.resource.ResourceException;

import io.skysail.api.responses.SkysailResponse;
import io.skysail.server.app.bb.BodyboosterApplication;
import io.skysail.server.app.bb.Goal;
import io.skysail.server.restlet.resources.EntityServerResource;

public class GoalResource extends EntityServerResource<Goal> {

	private BodyboosterApplication app;

	@Override
	protected void doInit() throws ResourceException {
		super.doInit();
		app = (BodyboosterApplication)getApplication();
	}

	@Override
	public SkysailResponse<?> eraseEntity() {
		app.getRepository().delete(getAttribute("id"));
		return new SkysailResponse<>();
	}

	@Override
	public Goal getEntity() {
		return app.getRepository().getById(getAttribute("id"));
	}

}
