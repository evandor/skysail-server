package io.skysail.server.app.bb;

import java.util.Arrays;
import java.util.List;

import de.twenty11.skysail.server.core.restlet.ResourceContextId;
import io.skysail.server.app.bb.areas.Area;

public class FinanceGoalsResource extends AreaGoalsResource {
    
    public FinanceGoalsResource() {
        super(Area.FINANCE);
    }

    @Override
    public List<Goal> getEntity() {
        return Arrays.asList(
                new Goal("first Goal", area),
                new Goal("second Goal", area),
                new Goal("third Goal", area)
                );
    }    



}
