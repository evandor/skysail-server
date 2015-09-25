package io.skysail.server.app.bb.goals.rf;

import io.skysail.api.responses.SkysailResponse;
import io.skysail.server.app.bb.BodyboosterApplication;
import io.skysail.server.app.bb.Goal;
import io.skysail.server.app.bb.areas.Area;
import io.skysail.server.restlet.resources.PostEntityServerResource;

public class PostRecreationAndFreetimeGoalsResource extends PostEntityServerResource<Goal> {

    @Override
    public Goal createEntityTemplate() {
        return new Goal("", Area.RECREATION_AND_FREETIME);
    }

    @Override
    public SkysailResponse<Goal> addEntity(Goal entity) {
        String id = ((BodyboosterApplication)getApplication()).getRepository().save(entity).toString();
        entity.setId(id);
        return new SkysailResponse<>(entity);
    }

}
