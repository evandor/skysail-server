package io.skysail.server.app.bb;

import java.util.Arrays;
import java.util.List;

import de.twenty11.skysail.server.core.restlet.ResourceContextId;
import io.skysail.server.app.bb.areas.Area;

public class PersonalGoalsResources extends AreaGoalsResource {
    
    public PersonalGoalsResources() {
        super(Area.PERSONAL_GOALS);
    }

    @Override
    public List<Goal> getEntity() {
        return Arrays.asList(
                new Goal("first Goal - " + area, area),
                new Goal("second Goal", area)
                );
    }
    

}
