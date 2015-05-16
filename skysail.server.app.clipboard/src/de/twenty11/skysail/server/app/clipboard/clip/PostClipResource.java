package de.twenty11.skysail.server.app.clipboard.clip;

import io.skysail.api.responses.SkysailResponse;
import io.skysail.server.restlet.resources.PostEntityServerResource;

import org.restlet.data.Form;
import org.restlet.resource.ResourceException;

import de.twenty11.skysail.server.app.clipboard.ClipboardApplication;
import de.twenty11.skysail.server.core.restlet.ResourceContextId;
import de.twenty11.skysail.server.events.EventHandler;

public class PostClipResource extends PostEntityServerResource<Clip> {

	private ClipboardApplication app;

	public PostClipResource() {
		setDescription("create a new clip");
		addToContext(ResourceContextId.LINK_TITLE, "Create new Clip");
	}
	
	@Override
	protected void doInit() throws ResourceException {
	    app = (ClipboardApplication) getApplication();
	}

	@Override
	public Clip getData(Form form) {
		Clip clip = populate(new Clip(), form);
		clip.setOwner(app.getCurrentUser());
		return clip;
	}

	@Override
	public SkysailResponse<?> addEntity(Clip entity) {
		entity = app.getClipsRepository().add(entity);
		String id = entity.getRid().toString().replace("#","");
//        Linkheader linkheader = ServerLink.fromResource(app, ClipResource.class);
//        if (linkheader != null) {
//            index(entity, app.getSearchService(), linkheader.getUri(), id);
//        }
		return new SkysailResponse<Clip>();
	}

	@Override
	public Clip createEntityTemplate() {
		return new Clip();
	}

	@Override
	public String redirectTo() {
        //EventHandler.sendEvent(SkysailEvents.GUI_ALERT_WARNING, "In-Memory database", "warning");
		return super.redirectTo(ClipsResource.class);
	}

}
