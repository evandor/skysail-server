package io.skysail.server.app.oil;

import java.util.List;

import io.skysail.api.links.Link;
import io.skysail.server.queryfilter.Filter;
import io.skysail.server.restlet.resources.ListServerResource;

public class ItemsResource extends ListServerResource<OpenItem> {

    private OilApplication app;

    @Override
    protected void doInit() {
        app = (OilApplication) getApplication();
    }

    @Override
    public List<OpenItem> getEntity() {
       return app.getRepository().find(new Filter(getRequest()));
    }

    @Override
    public List<Link> getLinks() {
       return super.getLinks(PostItemResource.class);
    }
 
 

}
