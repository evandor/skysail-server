package io.skysail.server.app.weightwatcher;

import io.skysail.server.queryfilter.Filter;
import io.skysail.server.restlet.resources.ListServerResource;
import io.skysail.api.links.Link;

import java.util.List;
import java.util.Map;

import de.twenty11.skysail.server.core.restlet.ResourceContextId;

public class MeasuresResource extends ListServerResource<io.skysail.server.app.weightwatcher.Measure> {

    private WeightWatcherApplication app;
    private MeasureRepository repository;

    public MeasuresResource() {
        super(MeasureResource.class);
        addToContext(ResourceContextId.LINK_TITLE, "list Measures");
    }

    @Override
    protected void doInit() {
        app = (WeightWatcherApplication) getApplication();
        repository = (MeasureRepository) app.getRepository(io.skysail.server.app.weightwatcher.Measure.class);
    }

    @Override
    public List<io.skysail.server.app.weightwatcher.Measure> getEntity() {
       return repository.find(new Filter(getRequest()));
    }

    public List<Link> getLinks() {
       return super.getLinks(PostMeasureResource.class);
    }
}