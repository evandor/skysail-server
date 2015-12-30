package io.skysail.server.app.weightwatcher;

import io.skysail.api.responses.SkysailResponse;
import io.skysail.server.restlet.resources.PutEntityServerResource;

import java.util.Date;
import org.restlet.resource.ResourceException;

public class PutMeasureResource extends PutEntityServerResource<io.skysail.server.app.weightwatcher.Measure> {


    private String id;
    private WeightWatcherApplication app;

    @Override
    protected void doInit() throws ResourceException {
        id = getAttribute("id");
        app = (WeightWatcherApplication)getApplication();
    }

    @Override
    public SkysailResponse<io.skysail.server.app.weightwatcher.Measure> updateEntity(Measure  entity) {
        io.skysail.server.app.weightwatcher.Measure original = getEntity();
        copyProperties(original,entity);

        app.getRepository(io.skysail.server.app.weightwatcher.Measure.class).update(id, original);
        return new SkysailResponse<>();
    }

    @Override
    public io.skysail.server.app.weightwatcher.Measure getEntity() {
        return (io.skysail.server.app.weightwatcher.Measure)app.getRepository(io.skysail.server.app.weightwatcher.Measure.class).findOne(id);
    }

    @Override
    public String redirectTo() {
        return super.redirectTo(MeasuresResource.class);
    }
}