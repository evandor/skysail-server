package de.twenty11.skysail.server.app.clipboard.clip;

import io.skysail.api.responses.SkysailResponse;
import io.skysail.server.restlet.resources.PutEntityServerResource;

import java.util.Date;

import org.restlet.resource.ResourceException;

import de.twenty11.skysail.server.app.clipboard.ClipboardApplication;

public class PutClipResource extends PutEntityServerResource<Clip> {

    private String id;
    private ClipboardApplication app;

    @Override
    protected void doInit() throws ResourceException {
        app = (ClipboardApplication) getApplication();
        id = getAttribute("id");
    }

    @Override
    public Clip getEntity() {
        return app.getClipsRepository().getById(id);
    }

    @Override
    public SkysailResponse<?> updateEntity(Clip entity) {
        entity.setModified(new Date());
        if (entity.getOwner() == null) {
            entity.setOwner(app.getCurrentUser());
        }
        System.out.println(entity.getRid());
        app.getClipsRepository().update(entity);
        return new SkysailResponse<String>();
    }
    
    @Override
	public String redirectTo() {
		return super.redirectTo(ClipsResource.class);
	}

}
