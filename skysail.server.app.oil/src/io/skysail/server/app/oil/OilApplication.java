package io.skysail.server.app.oil;

import java.util.Arrays;
import java.util.List;

import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.EventAdmin;

import de.twenty11.skysail.server.app.ApplicationProvider;
import de.twenty11.skysail.server.core.restlet.RouteBuilder;
import io.skysail.domain.core.repos.DbRepository;
import io.skysail.server.app.SkysailApplication;
import io.skysail.server.menus.MenuItem;
import io.skysail.server.menus.MenuItemProvider;

@org.osgi.service.component.annotations.Component(immediate = true)
public class OilApplication extends SkysailApplication implements ApplicationProvider, MenuItemProvider {

	private static final String APP_NAME = "OpenItemsList";

	@Reference(target = "(name=OilRepository)")
	private DbRepository oilRepository;

	@Override
	protected void attach() {
		router.attach(new RouteBuilder("/items", ItemsResource.class));
		router.attach(new RouteBuilder("/items/", PostItemResource.class));
		// router.attach(new RouteBuilder("/items/{id}", ClipResource.class));
		// router.attach(new RouteBuilder("/items/{id}/",
		// PutClipResource.class));
	}

	@Override
	public List<MenuItem> getMenuEntries() {
		MenuItem appMenu = new MenuItem(APP_NAME, "/" + APP_NAME + getApiVersion().getVersionPath());
		appMenu.setCategory(MenuItem.Category.APPLICATION_MAIN_MENU);
		return Arrays.asList(appMenu);
	}

	public OilRepository getRepository() {
		return (OilRepository) oilRepository;
	}

    @Override
    public EventAdmin getEventAdmin() {
        return null;
    }
}
