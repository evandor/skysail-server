package io.skysail.server.app.bb.goals.wc;

import io.skysail.api.responses.SkysailResponse;
import io.skysail.server.app.bb.BodyboosterApplication;
import io.skysail.server.app.bb.Goal;
import io.skysail.server.app.bb.areas.Area;
import io.skysail.server.restlet.resources.PostEntityServerResource;

public class PostWorkAndCareerGoalsResource extends PostEntityServerResource<Goal> {

    @Override
    public Goal createEntityTemplate() {
        return new Goal("", Area.WORK_AND_CAREERS);
    }

    @Override
    public SkysailResponse<Goal> addEntity(Goal entity) {
        String id = ((BodyboosterApplication)getApplication()).getRepository().save(entity).toString();
        entity.setId(id);
        return new SkysailResponse<>(entity);
    }

}
