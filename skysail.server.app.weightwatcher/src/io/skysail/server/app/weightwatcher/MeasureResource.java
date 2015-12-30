package io.skysail.server.app.weightwatcher;

import java.util.List;

import io.skysail.api.links.Link;
import io.skysail.api.responses.SkysailResponse;
import io.skysail.server.restlet.resources.EntityServerResource;
import de.twenty11.skysail.server.core.restlet.ResourceContextId;

public class MeasureResource extends EntityServerResource<io.skysail.server.app.weightwatcher.Measure> {

    private String id;
    private WeightWatcherApplication app;
    private MeasureRepository repository;

    public MeasureResource() {
        addToContext(ResourceContextId.LINK_TITLE, "details");
        addToContext(ResourceContextId.LINK_GLYPH, "search");
    }

    @Override
    protected void doInit() {
        id = getAttribute("id");
        app = (WeightWatcherApplication) getApplication();
        repository = (MeasureRepository) app.getRepository(io.skysail.server.app.weightwatcher.Measure.class);
    }


    @Override
    public SkysailResponse<?> eraseEntity() {
        repository.delete(id);
        return new SkysailResponse<>();
    }

    @Override
    public io.skysail.server.app.weightwatcher.Measure getEntity() {
        return (io.skysail.server.app.weightwatcher.Measure)app.getRepository().findOne(id);
    }

	@Override
    public List<Link> getLinks() {
        return super.getLinks(PutMeasureResource.class);
    }

}