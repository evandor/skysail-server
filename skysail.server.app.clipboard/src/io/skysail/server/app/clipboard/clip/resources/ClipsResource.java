package io.skysail.server.app.clipboard.clip.resources;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.restlet.data.Header;
import org.restlet.resource.ResourceException;
import org.restlet.util.Series;

import io.skysail.api.links.Link;
import io.skysail.server.ResourceContextId;
import io.skysail.server.app.clipboard.ClipboardApplication;
import io.skysail.server.app.clipboard.domain.Clip;
import io.skysail.server.restlet.resources.ListServerResource;
import io.skysail.server.utils.HeadersUtils;

public class ClipsResource extends ListServerResource<Clip> {

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

    // @Get("json")
    // public StringRepresentation getJson() {
    // ClientInfo ci = getRequest().getClientInfo();
    // logger.info("calling getEntities, media types '{}'", ci != null ?
    // ci.getAcceptedMediaTypes() : "test");
    // List<String> response = getEntitiesAsJson();
    // String result = "[" + Joiner.on(",").join(response) + "]";
    // return new StringRepresentation(result, MediaType.APPLICATION_JSON);
    // }

    @Override
    public List<Link> getLinks() {
        return super.getLinks(ClipboardAboutResource.class, PostClipResource.class);
    }

    @Override
    public List<Clip> getEntity() {
        int linesPerPage = 10;
        String username = SecurityUtils.getSubject().getPrincipal().toString();

        Series<Header> headers = HeadersUtils.getHeaders(getResponse());
        long clipCount = app.getClipsRepository().getClipCount(username);
        headers.add(new Header(HeadersUtils.PAGINATION_PAGES, Long.toString(1 + Math.floorDiv(clipCount, linesPerPage))));
        headers.add(new Header(HeadersUtils.PAGINATION_PAGE, Integer.toString(page)));
        headers.add(new Header(HeadersUtils.PAGINATION_HITS, Long.toString(clipCount)));
        return app.getClipsRepository().getClips(page, username, linesPerPage);
    }

}
