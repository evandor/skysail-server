package io.skysail.server.app.bb.goals;

import org.restlet.resource.ResourceException;

import io.skysail.server.app.bb.BBApplication;
import io.skysail.server.app.bb.Goal;
import io.skysail.server.restlet.resources.PutEntityServerResource;

public class PutAreaGoalsResource extends PutEntityServerResource<Goal> {

	private BBApplication app;

	@Override
	protected void doInit() throws ResourceException {
		super.doInit();
		app = (BBApplication)getApplication();
	}

    @Override
    public Goal getEntity() {
        return app.getRepository().getById(getAttribute("id"));
    }
	
}