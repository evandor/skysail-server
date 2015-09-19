package io.skysail.server.app.bb;

import java.util.Arrays;
import java.util.List;

import io.skysail.server.app.bb.areas.Area;

public class RecreationAndFreetimeGoalsResource extends AreaGoalsResource {
    
    public RecreationAndFreetimeGoalsResource() {
        super(Area.RECREATION_AND_FREETIME);
    }

    @Override
    public List<Goal> getEntity() {
        return Arrays.asList(
                new Goal("first Goal - " + area, area),
                new Goal("second Goal", area),
                new Goal("third Goal", area)
                );
    }    

}
