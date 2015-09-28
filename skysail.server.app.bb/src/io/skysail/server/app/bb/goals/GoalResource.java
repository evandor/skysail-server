package io.skysail.server.app.bb.goals;

import java.util.List;

import org.restlet.resource.ResourceException;

import io.skysail.api.links.Link;
import io.skysail.api.responses.SkysailResponse;
import io.skysail.server.app.bb.BBApplication;
import io.skysail.server.app.bb.Goal;
import io.skysail.server.restlet.resources.EntityServerResource;

public class GoalResource extends EntityServerResource<Goal> {

	private BBApplication app;

	@Override
	protected void doInit() throws ResourceException {
		super.doInit();
		app = (BBApplication)getApplication();
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
	
	@Override
	public List<Link> getLinks() {
	    return super.getLinks(PutGoalResource.class);
	}
	
//	@Override
//	public String redirectTo() {
//	    return super.redirectTo(GoalsResource.class);
//	}

}
