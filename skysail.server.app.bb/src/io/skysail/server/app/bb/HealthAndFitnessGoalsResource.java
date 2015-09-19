package io.skysail.server.app.bb;

import java.util.Arrays;
import java.util.List;

import de.twenty11.skysail.server.core.restlet.ResourceContextId;
import io.skysail.server.app.bb.areas.Area;

public class HealthAndFitnessGoalsResource extends AreaGoalsResource {
    
    public HealthAndFitnessGoalsResource() {
        super(Area.HEALTH_AND_FITNESS);
    }

    @Override
    public List<Goal> getEntity() {
        return Arrays.asList(
                new Goal("first Goal - " + area, area),
                new Goal("second Goal", area)
                );
    }
    

}
