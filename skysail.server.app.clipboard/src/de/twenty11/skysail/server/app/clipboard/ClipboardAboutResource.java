package de.twenty11.skysail.server.app.clipboard;

import io.skysail.server.restlet.resources.ListServerResource;

import java.util.ArrayList;
import java.util.List;

import de.twenty11.skysail.server.app.clipboard.clip.Clip;
import de.twenty11.skysail.server.app.clipboard.clip.ClipsResource;
import de.twenty11.skysail.server.app.clipboard.clip.PostClipResource;
import de.twenty11.skysail.server.core.restlet.ResourceContextId;

/**
 * Restlet Root Resource for notes application.
 *
 */
public class ClipboardAboutResource extends ListServerResource<Clip> {

    private ClipboardApplication app;

    public ClipboardAboutResource() {
        super(null);
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
