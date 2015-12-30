package io.skysail.server.app.weightwatcher;

import java.util.Date;

import io.skysail.api.responses.SkysailResponse;
import de.twenty11.skysail.server.core.restlet.ResourceContextId;
import io.skysail.server.restlet.resources.PostEntityServerResource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.restlet.resource.ResourceException;

public class PostMeasureResource extends PostEntityServerResource<io.skysail.server.app.weightwatcher.Measure> {

	private WeightWatcherApplication app;

    public PostMeasureResource() {
        addToContext(ResourceContextId.LINK_TITLE, "Create new ");
    }

    @Override
    protected void doInit() throws ResourceException {
        app = (WeightWatcherApplication) getApplication();
    }

    @Override
    public io.skysail.server.app.weightwatcher.Measure createEntityTemplate() {
        return new Measure();
    }

    @Override
    public void addEntity(io.skysail.server.app.weightwatcher.Measure entity) {
        Subject subject = SecurityUtils.getSubject();
        String id = app.getRepository(io.skysail.server.app.weightwatcher.Measure.class).save(entity, app.getApplicationModel()).toString();
        entity.setId(id);

    }

    @Override
    public String redirectTo() {
        return super.redirectTo(MeasuresResource.class);
    }
}