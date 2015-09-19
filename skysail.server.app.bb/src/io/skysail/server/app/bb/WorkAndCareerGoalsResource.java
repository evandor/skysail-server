package io.skysail.server.app.bb;

import java.util.Arrays;
import java.util.List;

import io.skysail.server.app.bb.areas.Area;

public class WorkAndCareerGoalsResource extends AreaGoalsResource {
    
    public WorkAndCareerGoalsResource() {
        super(Area.WORK_AND_CAREERS);
    }

    @Override
    public List<Goal> getEntity() {
        return Arrays.asList(
                new Goal("1. Vorsorge: Entwicklung einer Präsentation mit Produkten und Business Case", area)
                );
    }
    

}
