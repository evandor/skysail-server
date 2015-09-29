package io.skysail.server.app.bb.achievements;

import org.restlet.resource.ResourceException;

import io.skysail.api.responses.SkysailResponse;
import io.skysail.server.app.bb.BBApplication;
import io.skysail.server.app.bb.Goal;
import io.skysail.server.restlet.resources.PostEntityServerResource;

public class PostAchievementResource extends PostEntityServerResource<Achievement> {

    private BBApplication app;

    @Override
    protected void doInit() throws ResourceException {
        super.doInit();
        app = (BBApplication) getApplication();
    }

    @Override
    public Achievement createEntityTemplate() {
        return new Achievement();
    }

    @Override
    public SkysailResponse<Achievement> addEntity(Achievement entity) {
    	app.getRepository().save(entity).toString();
        Goal goal = app.getRepository().getById(getAttribute("id"));
        goal.getAchievements().add(entity);
        app.getRepository().update(getAttribute("id"), goal, "achievements");
        return new SkysailResponse<>(entity);
    }

}
