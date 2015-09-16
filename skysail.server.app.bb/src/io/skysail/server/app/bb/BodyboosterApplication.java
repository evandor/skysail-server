package io.skysail.server.app.bb;

import java.util.Arrays;
import java.util.List;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

import de.twenty11.skysail.server.app.ApplicationProvider;
import de.twenty11.skysail.server.core.restlet.RouteBuilder;
import de.twenty11.skysail.server.services.MenuItem;
import de.twenty11.skysail.server.services.MenuItemProvider;
import io.skysail.server.app.SkysailApplication;
import io.skysail.server.app.bb.areas.PostAreaResource;
import io.skysail.server.db.DbRepository;
import io.skysail.server.db.DbService;
import lombok.Getter;
import lombok.Setter;

@Component(immediate = true)
public class BodyboosterApplication extends SkysailApplication implements ApplicationProvider, MenuItemProvider {

    private static final String APP_NAME = "bodybooster";
    
    /*@Reference(policy = ReferencePolicy.DYNAMIC, cardinality = ReferenceCardinality.MANDATORY, target = "(name=BodyboosterRepository)")
    @Setter // for tests
    private DbRepository repository;*/
    
    @Reference(cardinality = ReferenceCardinality.OPTIONAL)
    @Setter // for tests
    @Getter
    private DbService dbService;

    public BodyboosterApplication() {
        super(APP_NAME);
    }
    
    @Activate
    public void activate() { // NO_UCD
        dbService.createWithSuperClass("V", Area.class.getSimpleName());
        dbService.register(Area.class);
    }
    
    @Override
    protected void attach() {
        super.attach();
        router.attach(new RouteBuilder("", AreasResource.class));
        router.attach(new RouteBuilder("/areas", AreasResource.class));
        router.attach(new RouteBuilder("/areas/", PostAreaResource.class));
    }

    @Override
    public List<MenuItem> getMenuEntries() {
        MenuItem menuItem = new MenuItem(APP_NAME, "/" + APP_NAME + getApiVersion().getVersionPath());
        menuItem.setCategory(MenuItem.Category.APPLICATION_MAIN_MENU);
        return Arrays.asList(menuItem);
    }

}
