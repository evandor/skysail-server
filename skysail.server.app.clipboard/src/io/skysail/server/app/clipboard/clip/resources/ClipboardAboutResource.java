package io.skysail.server.app.clipboard.clip.resources;

import java.util.List;

import io.skysail.server.ResourceContextId;
import io.skysail.server.app.clipboard.ClipboardApplication;
import io.skysail.server.app.clipboard.domain.Clip;
import io.skysail.server.restlet.resources.ListServerResource;

/**
 * Restlet Root Resource for notes application.
 *
 */
public class ClipboardAboutResource extends ListServerResource<Clip> {

    private ClipboardApplication app;

    public ClipboardAboutResource() {
        //super(null);
        app = (ClipboardApplication) getApplication();
        setDescription("Root Resource for skysail clipboard application");
        addToContext(ResourceContextId.LINK_TITLE, "About Clipboard Application");
    }

//    @Override
//    public List<Linkheader> getLinkheader() {
//         List<Linkheader> linkheader = super.getLinkheader(PostClipResource.class, ClipsResource.class);
//         //linkheader.add(new Linkheader("abc", LinkHeaderRelation.CREATE_FORM, "title"));
//         return linkheader;
//    }

	@Override
	public List<Clip> getEntity() {
		// TODO Auto-generated method stub
		return null;
	}
}
