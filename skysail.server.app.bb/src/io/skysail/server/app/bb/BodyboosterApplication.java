package io.skysail.server.app.bb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import de.twenty11.skysail.server.app.ApplicationProvider;
import de.twenty11.skysail.server.core.restlet.RouteBuilder;
import de.twenty11.skysail.server.services.MenuItem;
import de.twenty11.skysail.server.services.MenuItemProvider;
import io.skysail.server.app.SkysailApplication;
import io.skysail.server.app.bb.areas.PostAreaResource;
import io.skysail.server.app.bb.goals.GoalResource;
import io.skysail.server.app.bb.goals.GoalsResource;
import io.skysail.server.app.bb.goals.PostGoalResource;
import io.skysail.server.app.bb.goals.PutGoalResource;
import io.skysail.server.db.DbRepository;
import io.skysail.server.restlet.resources.SkysailServerResource;

@Component(immediate = true)
public class BodyboosterApplication extends SkysailApplication implements ApplicationProvider, MenuItemProvider {

    private static final String APP_NAME = "bodybooster";
    
    @Reference(target = "(name=BodyboosterRepository)")
    private DbRepository bbRepository;
    
    public BodyboosterApplication() {
        super(APP_NAME);
    }
    
    @Override
    protected void attach() {
        super.attach();
        router.attach(new RouteBuilder("", GoalsResource.class));
        router.attach(new RouteBuilder("/areas", AreasResource.class));
        router.attach(new RouteBuilder("/areas/", PostAreaResource.class));

        router.attach(new RouteBuilder("/goals", GoalsResource.class));
        router.attach(new RouteBuilder("/goals/", PostGoalResource.class));
        router.attach(new RouteBuilder("/goals/{id}", GoalResource.class));
        router.attach(new RouteBuilder("/goals/{id}/", PutGoalResource.class));
        
        router.attach(new RouteBuilder("/wc", WorkAndCareerGoalsResource.class));
        router.attach(new RouteBuilder("/rf", RecreationAndFreetimeGoalsResource.class));
        router.attach(new RouteBuilder("/f",  FinanceGoalsResource.class));
        router.attach(new RouteBuilder("/hf", HealthAndFitnessGoalsResource.class));
        router.attach(new RouteBuilder("/pg", PersonalGoalsResources.class));
        router.attach(new RouteBuilder("/rg", RelationshipGoalsResource.class));
        router.attach(new RouteBuilder("/c", ContributionGoalsResource.class));
        
    }

    @Override
    public List<MenuItem> getMenuEntries() {
        MenuItem menuItem = new MenuItem(APP_NAME, "/" + APP_NAME + getApiVersion().getVersionPath());
        menuItem.setCategory(MenuItem.Category.APPLICATION_MAIN_MENU);
        return Arrays.asList(menuItem);
    }
    
    public Repository getRepository() {
        return (Repository)bbRepository;
    }
    
    public List<Class<? extends SkysailServerResource<?>>> getMainLinks() {
        List<Class<? extends SkysailServerResource<?>>> result = new ArrayList<>();
        result.add(PostGoalResource.class);
        result.add(WorkAndCareerGoalsResource.class);
        result.add(RecreationAndFreetimeGoalsResource.class);
        result.add(FinanceGoalsResource.class);
        result.add(HealthAndFitnessGoalsResource.class);
        result.add(PersonalGoalsResources.class);
        result.add(RelationshipGoalsResource.class);
        result.add(ContributionGoalsResource.class);
        return result;
    }

}
