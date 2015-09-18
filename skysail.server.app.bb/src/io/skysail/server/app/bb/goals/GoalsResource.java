package io.skysail.server.app.bb.goals;

import java.util.List;

import org.restlet.resource.ResourceException;

import io.skysail.server.app.bb.BodyboosterApplication;
import io.skysail.server.app.bb.Goal;
import io.skysail.server.queryfilter.Filter;
import io.skysail.server.restlet.resources.ListServerResource;

public class GoalsResource extends ListServerResource<Goal> {

	private BodyboosterApplication app;

	@Override
	protected void doInit() throws ResourceException {
		super.doInit();
		app = (BodyboosterApplication)getApplication();
	}

	@Override
	public List<Goal> getEntity() {
		return app.getRepository().find(new Filter(getRequest()));
	}

}
