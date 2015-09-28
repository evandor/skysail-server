package io.skysail.server.app.bb.goals;

import io.skysail.api.responses.SkysailResponse;
import io.skysail.server.app.bb.BBApplication;
import io.skysail.server.app.bb.Goal;
import io.skysail.server.app.bb.goals.wc.WorkAndCareerGoalsResource;
import io.skysail.server.restlet.resources.PostEntityServerResource;

public abstract class PostAreaGoalsResource extends PostEntityServerResource<Goal> {

    @Override
    public SkysailResponse<Goal> addEntity(Goal entity) {
        String id = ((BBApplication)getApplication()).getRepository().save(entity).toString();
        entity.setId(id);
        return new SkysailResponse<>(entity);
    }

    @Override
    public String redirectTo() {
        return super.redirectTo(WorkAndCareerGoalsResource.class);
    }
}
