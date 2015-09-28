package io.skysail.server.app.bb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import de.twenty11.skysail.server.app.ApplicationProvider;
import de.twenty11.skysail.server.core.restlet.RouteBuilder;
import io.skysail.server.app.SkysailApplication;
import io.skysail.server.app.bb.achievements.PostAchievementResource;
import io.skysail.server.app.bb.areas.PostAreaResource;
import io.skysail.server.app.bb.goals.f.FinanceGoalsResource;
import io.skysail.server.app.bb.goals.f.PostFinanceGoalsResource;
import io.skysail.server.app.bb.goals.hf.HealthAndFitnessGoalsResource;
import io.skysail.server.app.bb.goals.hf.PostHealthAndFitnessGoalsResource;
import io.skysail.server.app.bb.goals.pg.PersonalGoalsResource;
import io.skysail.server.app.bb.goals.pg.PostPersonalGoalsResource;
import io.skysail.server.app.bb.goals.rf.PostRecreationAndFreetimeGoalResource;
import io.skysail.server.app.bb.goals.rf.PutRecreationAndFreetimeGoalResource;
import io.skysail.server.app.bb.goals.rf.RecreationAndFreetimeGoalResource;
import io.skysail.server.app.bb.goals.rf.RecreationAndFreetimeGoalsResource;
import io.skysail.server.app.bb.goals.wc.PostWorkAndCareerGoalResource;
import io.skysail.server.app.bb.goals.wc.PutWorkAndCareerGoalResource;
import io.skysail.server.app.bb.goals.wc.WorkAndCareerGoalResource;
import io.skysail.server.app.bb.goals.wc.WorkAndCareerGoalsResource;
import io.skysail.server.menus.MenuItem;
import io.skysail.server.menus.MenuItemProvider;
import io.skysail.server.repo.DbRepository;
import io.skysail.server.restlet.resources.SkysailServerResource;

@Component(immediate = true)
public class BBApplication extends SkysailApplication implements ApplicationProvider, MenuItemProvider {

    private static final String APP_NAME = "bodybooster";
    
    @Reference(target = "(name=BodyboosterRepository)")
    private DbRepository bbRepository;

    @Reference(target = "(name=AchievementRepository)")
    private DbRepository achievementRepository;

    public BBApplication() {
        super(APP_NAME);
    }
    
    @Override
    protected void attach() {
        super.attach();
        router.attach(new RouteBuilder("", WorkAndCareerGoalsResource.class));
        router.attach(new RouteBuilder("/areas", AreasResource.class));
        router.attach(new RouteBuilder("/areas/", PostAreaResource.class));

        router.attach(new RouteBuilder("/wc", WorkAndCareerGoalsResource.class));
        router.attach(new RouteBuilder("/wc/", PostWorkAndCareerGoalResource.class));
        router.attach(new RouteBuilder("/wc/{id}", WorkAndCareerGoalResource.class));
        router.attach(new RouteBuilder("/wc/{id}/", PutWorkAndCareerGoalResource.class));
        
        router.attach(new RouteBuilder("/rf", RecreationAndFreetimeGoalsResource.class));
        router.attach(new RouteBuilder("/rf/", PostRecreationAndFreetimeGoalResource.class));
        router.attach(new RouteBuilder("/rf/{id}", RecreationAndFreetimeGoalResource.class));
        router.attach(new RouteBuilder("/rf/{id}/", PutRecreationAndFreetimeGoalResource.class));

        router.attach(new RouteBuilder("/f",  FinanceGoalsResource.class));
        router.attach(new RouteBuilder("/f/",  PostFinanceGoalsResource.class));
        
        router.attach(new RouteBuilder("/hf", HealthAndFitnessGoalsResource.class));
        router.attach(new RouteBuilder("/hf/", PostHealthAndFitnessGoalsResource.class));

        router.attach(new RouteBuilder("/pg", PersonalGoalsResource.class));
        router.attach(new RouteBuilder("/pg/", PostPersonalGoalsResource.class));
        
        router.attach(new RouteBuilder("/wc/{id}/achievement/", PostAchievementResource.class));

        
    }

    @Override
    public List<MenuItem> getMenuEntries() {
        MenuItem menuItem = new MenuItem(APP_NAME, "/" + APP_NAME + getApiVersion().getVersionPath());
        menuItem.setCategory(MenuItem.Category.APPLICATION_MAIN_MENU);
        return Arrays.asList(menuItem);
    }
    
    public BBRepository getRepository() {
        return (BBRepository) bbRepository;
    }
    
    public AchievementRepository getAchievementRepository() {
        return (AchievementRepository)achievementRepository;
    }
    
    public List<Class<? extends SkysailServerResource<?>>> getMainLinks() {
        List<Class<? extends SkysailServerResource<?>>> result = new ArrayList<>();
        result.add(WorkAndCareerGoalsResource.class);
        result.add(RecreationAndFreetimeGoalsResource.class);
        result.add(FinanceGoalsResource.class);
        result.add(HealthAndFitnessGoalsResource.class);
        result.add(PersonalGoalsResource.class);
        //result.add(RelationshipGoalsResource.class);
        //result.add(ContributionGoalsResource.class);
        return result;
    }

}
