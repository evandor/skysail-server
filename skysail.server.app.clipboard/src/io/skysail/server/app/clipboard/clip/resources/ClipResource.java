package io.skysail.server.app.clipboard.clip.resources;

import io.skysail.api.responses.SkysailResponse;
import io.skysail.server.app.clipboard.ClipboardApplication;
import io.skysail.server.app.clipboard.domain.Clip;
import io.skysail.server.restlet.resources.EntityServerResource;

import java.util.List;

import org.restlet.data.Form;
import org.restlet.resource.ResourceException;

public class ClipResource extends EntityServerResource<Clip> {

    private String id;
    private ClipboardApplication app;

    public ClipResource() {
        app = (ClipboardApplication) getApplication();
        setDescription("lets you access a single clip");
    }

    @Override
    protected void doInit() throws ResourceException {
        id = getAttribute("id");
    }
    
//    @Override
//    public Clip getData() {
//        return app.getClipsRepository().getById(id);
//    }
//    
    @Override
    protected String getDataAsJson() {
        return app.getClipsRepository().getByIdAsJson(id);
    }
    
//    public Clip getData(Form form) {
//        id = form.getFirstValue("@rid");
//        Clip data = getData();
//        data.setContent(form.getFirstValue("content"));
//        return data;
//    }

    
//    @Override
//    public Consumer<? super Linkheader> getPathSubstitutions() {
//        return l -> {
//            l.substitute("id", id);
//        };
//    }
    
//    @Override
//    public List<Linkheader> getLinkheader() {
//         return super.getLinkheader(ClipResource.class, PutClipResource.class);
//    }

    @Override
    public SkysailResponse<?> eraseEntity() {
        app.getClipsRepository().delete(id);
        return null;
    }

    @Override
    public String getId() {
        return id;
    }

	@Override
	public Clip getEntity() {
		// TODO Auto-generated method stub
		return null;
	}

}
