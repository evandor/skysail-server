package io.skysail.server.app.bb.goals.rf;

import io.skysail.server.app.bb.Goal;
import io.skysail.server.app.bb.areas.Area;
import io.skysail.server.app.bb.goals.PostAreaGoalsResource;

public class PostRecreationAndFreetimeGoalsResource extends PostAreaGoalsResource {

    @Override
    public Goal createEntityTemplate() {
        return new Goal("", Area.RECREATION_AND_FREETIME);
    }


}
