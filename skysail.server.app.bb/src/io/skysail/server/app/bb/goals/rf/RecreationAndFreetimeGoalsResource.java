package io.skysail.server.app.bb.goals.rf;

import java.util.List;

import org.restlet.resource.ResourceException;

import io.skysail.api.links.Link;
import io.skysail.server.app.bb.AreaGoalsResource;
import io.skysail.server.app.bb.BodyboosterApplication;
import io.skysail.server.app.bb.Goal;
import io.skysail.server.app.bb.areas.Area;
import io.skysail.server.queryfilter.Filter;

public class RecreationAndFreetimeGoalsResource extends AreaGoalsResource {
    
    public RecreationAndFreetimeGoalsResource() {
        super(Area.RECREATION_AND_FREETIME);
    }

    @Override
    protected void doInit() throws ResourceException {
        super.doInit();
        app = (BodyboosterApplication)getApplication();
    }

    @Override
    public List<Goal> getEntity() {
        Filter filter = new Filter(getRequest());
        filter.add("area", Area.WORK_AND_CAREERS.name());
        return app.getRepository().find(filter);
    }
    
    @Override
    public List<Link> getLinks() {
        List<Link> result = super.getLinks(app.getMainLinks());
        result.addAll(super.getLinks(PostRecreationAndFreetimeGoalsResource.class));
        return result;
    }
    

}
