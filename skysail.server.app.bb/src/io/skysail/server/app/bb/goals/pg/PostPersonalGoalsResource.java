package io.skysail.server.app.bb.goals.pg;

import io.skysail.server.app.bb.Goal;
import io.skysail.server.app.bb.areas.Area;
import io.skysail.server.app.bb.goals.PostAreaGoalsResource;

public class PostPersonalGoalsResource extends PostAreaGoalsResource {

    @Override
    public Goal createEntityTemplate() {
        return new Goal("", Area.PERSONAL_GOALS);
    }

}
