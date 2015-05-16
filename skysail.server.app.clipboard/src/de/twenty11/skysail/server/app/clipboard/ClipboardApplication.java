package de.twenty11.skysail.server.app.clipboard;

import io.skysail.server.app.SkysailApplication;
import io.skysail.server.db.DbService2;

import java.util.Arrays;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;
import de.twenty11.skysail.server.app.ApplicationProvider;
import de.twenty11.skysail.server.app.clipboard.clip.ClipResource;
import de.twenty11.skysail.server.app.clipboard.clip.ClipsRepository;
import de.twenty11.skysail.server.app.clipboard.clip.ClipsResource;
import de.twenty11.skysail.server.app.clipboard.clip.PostClipResource;
import de.twenty11.skysail.server.app.clipboard.clip.PutClipResource;
import de.twenty11.skysail.server.core.restlet.ApplicationContextId;
import de.twenty11.skysail.server.core.restlet.RouteBuilder;
import de.twenty11.skysail.server.services.MenuItem;
import de.twenty11.skysail.server.services.MenuItemProvider;
import de.twenty11.skysail.server.services.SearchService;
import de.twenty11.skysail.server.services.SkysailThreadPool;
import de.twenty11.skysail.server.services.UserManager;
import de.twenty11.skysail.server.um.domain.SkysailUser;

/**
 * The restlet application defined in this bundle.
 *
 */
@Component
public class ClipboardApplication extends SkysailApplication implements ApplicationProvider, MenuItemProvider {

    public static final String APP_NAME = "clipboard";

    public static final String PATH_CLIPS = "/clips";

    public static final String PARAMETER_GROUP = "group";

    private volatile DbService2 dbService;
    private volatile ClipsRepository clipsRepository;
    private volatile UserManager userManager;

    private volatile SkysailThreadPool skysailThreadPool;

	private SearchService searchService;

    public ClipboardApplication() {
        super(APP_NAME);
        setDescription("RESTful skysail.server.app.clipboard bundle");
        setOwner("twentyeleven");
        setName(APP_NAME);
		addToAppContext(ApplicationContextId.IMG, "/static/img/silk/note.png");
    }

    @Override
    protected void attach() {
        super.attach();
        
        // @formatter:off
//        router.attach(new RouteBuilder("/api", ApiResource.class));
//        router.attach(new RouteBuilder("/entities", EntitiesResource.class));
//        router.attach(new RouteBuilder("/entities/{name}", EntitiesResource.class));

        router.attach(new RouteBuilder("", ClipsResource.class));
        router.attach(new RouteBuilder("/", ClipsResource.class));
        router.attach(new RouteBuilder(PATH_CLIPS, ClipsResource.class));
        router.attach(new RouteBuilder(PATH_CLIPS + "/", PostClipResource.class));
        router.attach(new RouteBuilder(PATH_CLIPS + "/{id}", ClipResource.class));
        router.attach(new RouteBuilder(PATH_CLIPS + "/{id}/", PutClipResource.class));
        router.attach(new RouteBuilder("/about", ClipboardAboutResource.class));
        // @formatter:on
    }

    @Reference
    public synchronized void setDbService(DbService2 dbService) {
        this.dbService = dbService;
    }

    public synchronized void unsetDbService(DbService2 emf) {
        this.dbService = null;
    }

    @Reference
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public void unsetUserManager(UserManager userManager) {
        this.userManager = null;
    }

    @Reference(dynamic = true, optional = true, multiple = false)
    public void setSkysailThreadPool(SkysailThreadPool skysailThreadPool) {
        this.skysailThreadPool = skysailThreadPool;
    }

    public void unsetSkysailThreadPool(@SuppressWarnings("unused") SkysailThreadPool skysailThreadPool) {
        this.skysailThreadPool = null;
    }

    @Reference(multiple = false, dynamic = true, optional = true)
    public synchronized void setSearchService(SearchService SearchService) {
        this.searchService = SearchService;
    }

    public synchronized void unsetSearchService(@SuppressWarnings("unused") SearchService searchService) {
        this.searchService = null;
    }
   
    public SkysailUser getCurrentUser() {
        Subject subject = SecurityUtils.getSubject();
        return userManager.findByUsername((String) subject.getPrincipal());
    }

    public synchronized ClipsRepository getClipsRepository() {
        if (this.clipsRepository == null) {
            this.clipsRepository = new ClipsRepository(dbService);
        }
        return this.clipsRepository;
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

	@Override
    public List<MenuItem> getMenuEntries() {
		MenuItem appMenu = new MenuItem("Clipboard", "/clipboard", this);
    	appMenu.setCategory(MenuItem.Category.APPLICATION_MAIN_MENU);
    	// TODO new MenuItem(appMenu, "add new note", "notes?media=htmlform");
        return Arrays.asList(appMenu);
    }

   

}
