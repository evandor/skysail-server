package io.skysail.server.app.bb.goals.f;

import io.skysail.server.app.bb.Goal;
import io.skysail.server.app.bb.areas.Area;
import io.skysail.server.app.bb.goals.PostAreaGoalsResource;

public class PostFinanceGoalsResource extends PostAreaGoalsResource {

    @Override
    public Goal createEntityTemplate() {
        return new Goal("", Area.FINANCE);
    }

}
