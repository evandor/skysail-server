package de.twenty11.skysail.server.app.clipboard.clip;

import io.skysail.server.restlet.resources.ListServerResource;

import java.util.List;

import org.restlet.resource.ResourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.server.app.clipboard.ClipboardAboutResource;
import de.twenty11.skysail.server.app.clipboard.ClipboardApplication;
import de.twenty11.skysail.server.core.restlet.ResourceContextId;

public class ClipsResource extends ListServerResource<Clip> {

    private static final Logger logger = LoggerFactory.getLogger(ClipsResource.class);
    
    private ClipboardApplication app;

    private int page = 1;

    public ClipsResource() {
        super(ClipResource.class);
        app = (ClipboardApplication) getApplication();
        setDescription("deals with a list of clips");
        addToContext(ResourceContextId.LINK_TITLE, "List Clips");
    }
    
    @Override
    protected void doInit() throws ResourceException {
        String pageAsString = getQueryValue("page");
        if (pageAsString != null && pageAsString.trim().length() > 0) {
            page = Integer.parseInt(pageAsString);
        }
    }
    
//    @Get("json")
//    public StringRepresentation getJson() {
//        ClientInfo ci = getRequest().getClientInfo();
//        logger.info("calling getEntities, media types '{}'", ci != null ? ci.getAcceptedMediaTypes() : "test");
//        List<String> response = getEntitiesAsJson();
//        String result = "[" + Joiner.on(",").join(response) + "]";
//        return new StringRepresentation(result, MediaType.APPLICATION_JSON);
//    }
    
//	@Override
//    public List<Clip> getData() {
//		int linesPerPage = 10;
//        Object currentUserRid = app.getCurrentUser().getRid();
//
//        Series<Header> headers = HeadersUtils.getHeaders(getResponse());
//	    long clipCount = app.getClipsRepository().getClipCount(currentUserRid);
//	    headers.add(new Header(HeadersUtils.PAGINATION_PAGES,Long.toString(1 + Math.floorDiv(clipCount,linesPerPage))));
//        headers.add(new Header(HeadersUtils.PAGINATION_PAGE,Integer.toString(page)));
//        headers.add(new Header(HeadersUtils.PAGINATION_HITS, Long.toString(clipCount)));
//	    return app.getClipsRepository().getClips(page,currentUserRid,linesPerPage);
//    }
	
//	@Override
//	protected List<String> getDataAsJson() {
//		return app.getClipsRepository().getClipsAsJson();
//	}
	
//	@Override
//	public List<String> getFields() {
//	    return Arrays.asList("tags","content", "created");
//	}
	
//	@Override
//	public List<Linkheader> getLinkheader() {
//	    return super.getLinkheader(ClipboardAboutResource.class, PostClipResource.class);
//	}

	@Override
	public List<Clip> getEntity() {
		// TODO Auto-generated method stub
		return null;
	}

}
