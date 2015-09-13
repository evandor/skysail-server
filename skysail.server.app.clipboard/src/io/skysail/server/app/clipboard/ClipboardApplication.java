package io.skysail.server.app.clipboard;

import io.skysail.api.search.SearchService;
import io.skysail.server.app.SkysailApplication;
import io.skysail.server.app.clipboard.clip.resources.ClipResource;
import io.skysail.server.app.clipboard.clip.resources.ClipboardAboutResource;
import io.skysail.server.app.clipboard.clip.resources.ClipsResource;
import io.skysail.server.app.clipboard.clip.resources.PostClipResource;
import io.skysail.server.app.clipboard.clip.resources.PutClipResource;
import io.skysail.server.app.clipboard.repo.ClipsRepository;
import io.skysail.server.db.DbRepository;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;
import de.twenty11.skysail.server.app.ApplicationProvider;
import de.twenty11.skysail.server.core.restlet.ApplicationContextId;
import de.twenty11.skysail.server.core.restlet.RouteBuilder;
import de.twenty11.skysail.server.services.MenuItem;
import de.twenty11.skysail.server.services.MenuItemProvider;
import de.twenty11.skysail.server.services.SkysailThreadPool;
import de.twenty11.skysail.server.services.UserManager;
import de.twenty11.skysail.server.um.domain.SkysailUser;

/**
 * The restlet application defined in this bundle.
 *
 */
@Component(immediate = true)
public class ClipboardApplication extends SkysailApplication implements ApplicationProvider, MenuItemProvider {

    public static final String APP_NAME = "clipboard";
    public static final String PATH_CLIPS = "/clips";
    public static final String PARAMETER_GROUP = "group";

    private AtomicReference<ClipsRepository> clipsRepository = new AtomicReference<>();
    private volatile UserManager userManager;

    private volatile SkysailThreadPool skysailThreadPool;

	private SearchService searchService;

    public ClipboardApplication() {
        super(APP_NAME);
        addToAppContext(ApplicationContextId.IMG, "/static/img/silk/page_link.png");
    }

    @Reference(dynamic = true, multiple = false, optional = false, target = "(name=ClipsRepository)")
    public void setRepository(DbRepository repo) {
        this.clipsRepository.set((ClipsRepository) repo);
    }

    public void unsetRepository(DbRepository repo) {
        this.clipsRepository.compareAndSet((ClipsRepository)repo, null);
    }

    public ClipsRepository getClipsRepository() {
        return clipsRepository.get();
    }


    @Override
    protected void attach() {
        super.attach();

        router.attach(new RouteBuilder("", ClipsResource.class));
        router.attach(new RouteBuilder("/", ClipsResource.class));
        router.attach(new RouteBuilder(PATH_CLIPS, ClipsResource.class));
        router.attach(new RouteBuilder(PATH_CLIPS + "/", PostClipResource.class));
        router.attach(new RouteBuilder(PATH_CLIPS + "/{id}", ClipResource.class));
        router.attach(new RouteBuilder(PATH_CLIPS + "/{id}/", PutClipResource.class));
        router.attach(new RouteBuilder("/about", ClipboardAboutResource.class));
    }

    public SkysailUser getCurrentUser() {
        Subject subject = SecurityUtils.getSubject();
        return userManager.findByUsername((String) subject.getPrincipal());
    }

    public SkysailThreadPool getSkysailThreadPool() {
        return skysailThreadPool;
    }

    public SearchService getSearchService() {
	    return searchService;
    }

    public static String getPostNewNotePath() {
        return "/" + APP_NAME + PATH_CLIPS + "/";
    }

    public List<MenuItem> getMenuEntries() {
        MenuItem appMenu = new MenuItem(APP_NAME, "/" + APP_NAME + getApiVersion().getVersionPath(), this);
        appMenu.setCategory(MenuItem.Category.APPLICATION_MAIN_MENU);
        return Arrays.asList(appMenu);
    }



}
