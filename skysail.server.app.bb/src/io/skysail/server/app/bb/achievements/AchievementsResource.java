package io.skysail.server.app.bb.achievements;

import java.util.List;

import de.twenty11.skysail.server.core.restlet.ResourceContextId;
import io.skysail.server.app.bb.BBApplication;
import io.skysail.server.queryfilter.Filter;
import io.skysail.server.restlet.resources.ListServerResource;

public class AchievementsResource extends ListServerResource<Achievement>{

	private BBApplication app;

	public AchievementsResource() {
		addToContext(ResourceContextId.LINK_TITLE, "Achievements");
	}
	
	@Override
    protected void doInit() {
		super.doInit();
        app = (BBApplication) getApplication();
    }

	
	@Override
	public List<Achievement> getEntity() {
		return app.getRepository().find(new Filter().add("id", getAttribute("id"))).get(0).getAchievements();
	}

}
