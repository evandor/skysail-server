package io.skysail.server.app.bb.areas;

import org.restlet.resource.ResourceException;

import io.skysail.api.responses.SkysailResponse;
import io.skysail.server.app.bb.Area;
import io.skysail.server.app.bb.BodyboosterApplication;
import io.skysail.server.restlet.resources.PostEntityServerResource;

public class PostAreaResource extends PostEntityServerResource<Area> {

    private BodyboosterApplication app;

    @Override
    protected void doInit() throws ResourceException {
        super.doInit();
        app = (BodyboosterApplication)getApplication();
    }
    
    @Override
    public Area createEntityTemplate() {
        return new Area();
    }

    @Override
    public SkysailResponse<Area> addEntity(Area entity) {
        //app.getRepository().persist(entity);
        return new SkysailResponse<>(entity);
    }

}
