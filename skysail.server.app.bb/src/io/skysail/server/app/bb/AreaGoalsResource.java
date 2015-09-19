package io.skysail.server.app.bb;

import java.util.List;

import org.restlet.resource.ResourceException;

import de.twenty11.skysail.server.core.restlet.ResourceContextId;
import io.skysail.api.links.Link;
import io.skysail.api.responses.SkysailResponse;
import io.skysail.server.app.bb.areas.Area;
import io.skysail.server.restlet.resources.ListServerResource;

public abstract class AreaGoalsResource extends ListServerResource<Goal> {

    private BodyboosterApplication app;

    protected Area area;

    public AreaGoalsResource(Area area) {
        this.area = area; 
        addToContext(ResourceContextId.LINK_TITLE, area.toString());
    }

    @Override
    protected void doInit() throws ResourceException {
        super.doInit();
        app = (BodyboosterApplication) getApplication();
    }
    
    @Override
    public SkysailResponse<?> eraseEntity() {
        return null;
    }

    @Override
    public List<Link> getLinks() {
        return super.getLinks(app.getMainLinks());
    }

}
