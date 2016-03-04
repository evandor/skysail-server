package io.skysail.server.app.clipboard.clip.resources;

import io.skysail.api.responses.SkysailResponse;
import io.skysail.server.app.clipboard.ClipboardApplication;
import io.skysail.server.app.clipboard.domain.Clip;
import io.skysail.server.restlet.resources.PostEntityServerResource;

import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.restlet.resource.ResourceException;

import de.twenty11.skysail.server.core.restlet.ResourceContextId;

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
    public Clip createEntityTemplate() {
        return new Clip();
    }

	@Override
	public void addEntity(Clip entity) {
	    entity.setCreated(new Date());
        Subject subject = SecurityUtils.getSubject();
        subject.getPrincipals().getPrimaryPrincipal();
        entity.setOwner(subject.getPrincipal().toString());
        String id = app.getClipsRepository().add(entity).toString();
        entity.setId(id);
	}

	@Override
	public String redirectTo() {
		return super.redirectTo(ClipsResource.class);
	}

}
