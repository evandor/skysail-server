package io.skysail.server.app.clipboard.clip.resources;

import io.skysail.api.responses.SkysailResponse;
import io.skysail.server.app.clipboard.ClipboardApplication;
import io.skysail.server.app.clipboard.domain.Clip;
import io.skysail.server.restlet.resources.PutEntityServerResource;

import java.util.Date;

import org.restlet.resource.ResourceException;

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
        Clip original = getEntity();
        original.setContent(entity.getContent());
        original.setModified(new Date());
        app.getClipsRepository().update(original);
        return new SkysailResponse<>();
    }

    @Override
    public String redirectTo() {
        return super.redirectTo(ClipsResource.class);
    }

}
