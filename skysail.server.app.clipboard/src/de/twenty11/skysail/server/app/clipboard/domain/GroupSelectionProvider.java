package de.twenty11.skysail.server.app.clipboard.domain;

import io.skysail.api.forms.SelectionProvider;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.restlet.resource.Resource;


public class GroupSelectionProvider implements SelectionProvider {

    public GroupSelectionProvider() {
    }

    @Override
    public Map<String, String> getSelections() {
        Subject currentUser = SecurityUtils.getSubject();
        String username = (String) currentUser.getPrincipal();

        Map<String, String> result = new HashMap<String, String>();
        return result;
    }

    @Override
    public void setConfiguration(Object osgiServicesProvider) {
    }

    @Override
    public void setResource(Resource resource) {
    }

}
