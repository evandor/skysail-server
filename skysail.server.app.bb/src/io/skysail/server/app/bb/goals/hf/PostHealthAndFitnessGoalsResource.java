package io.skysail.server.app.bb.goals.hf;

import io.skysail.server.app.bb.Goal;
import io.skysail.server.app.bb.areas.Area;
import io.skysail.server.app.bb.goals.PostAreaGoalsResource;

public class PostHealthAndFitnessGoalsResource extends PostAreaGoalsResource {

    @Override
    public Goal createEntityTemplate() {
        return new Goal("", Area.HEALTH_AND_FITNESS);
    }

}
