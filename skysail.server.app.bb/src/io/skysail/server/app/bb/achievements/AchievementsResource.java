package io.skysail.server.app.bb.achievements;

import java.util.List;

import de.twenty11.skysail.server.core.restlet.ResourceContextId;
import io.skysail.server.app.bb.BBApplication;
import io.skysail.server.app.bb.Goal;
import io.skysail.server.restlet.resources.ListServerResource;

public class AchievementsResource extends ListServerResource<Achievement> {

    private BBApplication app;

    public AchievementsResource() {
        addToContext(ResourceContextId.LINK_GLYPH, "th-list");
    }
    
    @Override
    protected void doInit() {
        app = (BBApplication) getApplication();
    }

    @Override
    public List<Achievement> getEntity() {
      Goal theGoal = app.getRepository().findOne(getAttribute("id"));
      return theGoal.getAchievements();
    }

}
