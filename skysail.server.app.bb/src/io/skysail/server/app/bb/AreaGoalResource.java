package io.skysail.server.app.bb;

import org.restlet.resource.ResourceException;

import io.skysail.api.responses.SkysailResponse;
import io.skysail.server.app.bb.areas.Area;
import io.skysail.server.restlet.resources.EntityServerResource;

public abstract class AreaGoalResource extends EntityServerResource<Goal> {

    protected BBApplication app;

    protected Area area;

    @Override
    protected void doInit() throws ResourceException {
        super.doInit();
        app = (BBApplication) getApplication();
    }
    
    @Override
    public Goal getEntity() {
        return null;//app.getRepository().getById(getAttribute("id"));
    }

    
    @Override
    public SkysailResponse<?> eraseEntity() {
        app.getRepository().delete(getAttribute("id"));
        return new SkysailResponse<>();
    }

}
