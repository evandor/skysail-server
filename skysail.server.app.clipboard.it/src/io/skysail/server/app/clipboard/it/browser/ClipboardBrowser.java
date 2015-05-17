package io.skysail.server.app.clipboard.it.browser;

import lombok.extern.slf4j.Slf4j;

import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.data.Method;
import org.restlet.representation.Representation;

import io.skysail.server.app.clipboard.ClipboardApplication;
import io.skysail.server.app.clipboard.domain.Clip;
import io.skysail.client.testsupport.ApplicationBrowser;
import io.skysail.client.testsupport.ApplicationClient;

@Slf4j
public class ClipboardBrowser extends ApplicationBrowser<ClipboardBrowser, Clip> {

    public ClipboardBrowser(MediaType mediaType, String port) {
        super(ClipboardApplication.APP_NAME, mediaType, port);
    }

    public String createClip(Clip Clip) {
        log.info("{}creating Clip {}", ApplicationClient.TESTTAG, Clip);
        login();
        navigateToPostClipAs(client);
        client.post(createForm(Clip));
        return client.getLocation().getLastSegment(true);
    }

    public Representation getClips() {
        log.info("{}retrieving Clips", ApplicationClient.TESTTAG);
        login();
        getClips(client);
        return client.getCurrentRepresentation();
    }

    public void deleteClip(String id) {
        log.info("{}deleting Clip #{}", ApplicationClient.TESTTAG, id);
        login();
        deleteClip(client, id);
    }

    public Representation getClip(String id) {
        log.info("{}retrieving Clip #{}", ApplicationClient.TESTTAG, id);
        login();
        getClip(client, id);
        return client.getCurrentRepresentation();
    }

    public void updateClip(Clip theClip) {
        //log.info("{}updating Clip #{}", ApplicationClient.TESTTAG, theClip.getId());
        login();
       // updateClip(client, theClip);
    }

    @Override
    protected Form createForm(Clip clip) {
        return new Form() {{
                add("content", clip.getContent());
            }};
    }

    private void getClips(ApplicationClient<Clip> client) {
        client.gotoRoot();
        client.followLinkTitle(ClipboardApplication.APP_NAME);
    }

//    private void updateClip(ApplicationClient<Clip> client, Clip theClip) {
//        client.gotoAppRoot().followLinkTitleAndRefId("update", theClip.getId()).followLink(Method.PUT, theClip);
//    }

    private void getClip(ApplicationClient<?> client, String id) {
        client.gotoRoot().followLinkTitle(ClipboardApplication.APP_NAME);
    }

    private void deleteClip(ApplicationClient<?> client, String id) {
        client.gotoAppRoot() //
                .followLinkTitleAndRefId("update", id).followLink(Method.DELETE, null);
    }

    private void navigateToPostClipAs(ApplicationClient<Clip> client) {
        client.gotoAppRoot().followLinkTitle("Create new Clip");
    }

}
