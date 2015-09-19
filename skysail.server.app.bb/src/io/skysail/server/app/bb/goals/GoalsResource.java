package io.skysail.server.app.bb.goals;

import java.util.List;

import org.restlet.resource.ResourceException;

import io.skysail.api.links.Link;
import io.skysail.server.app.bb.BodyboosterApplication;
import io.skysail.server.app.bb.Goal;
import io.skysail.server.queryfilter.Filter;
import io.skysail.server.restlet.resources.ListServerResource;

public class GoalsResource extends ListServerResource<Goal> {

	private BodyboosterApplication app;

	public GoalsResource() {
        super(GoalResource.class);
    }
	
	@Override
	protected void doInit() throws ResourceException {
		super.doInit();
		app = (BodyboosterApplication)getApplication();
	}

	@Override
	public List<Goal> getEntity() {
		return app.getRepository().find(new Filter(getRequest()));
	}
	
	@Override
	public List<Link> getLinks() {
	    return super.getLinks(app.getMainLinks());
	}

}
