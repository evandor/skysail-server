package io.skysail.server.app.bb.areas;

import org.restlet.resource.ResourceException;

import com.orientechnologies.orient.core.id.ORecordId;

import io.skysail.api.responses.SkysailResponse;
import io.skysail.server.app.bb.AreaOld;
import io.skysail.server.app.bb.BBApplication;
import io.skysail.server.restlet.resources.PostEntityServerResource;

public class PostAreaResource extends PostEntityServerResource<AreaOld> {

    private BBApplication app;

    @Override
    protected void doInit() throws ResourceException {
        super.doInit();
        app = (BBApplication)getApplication();
    }
    
    @Override
    public AreaOld createEntityTemplate() {
        return new AreaOld();
    }

    @Override
    public SkysailResponse<AreaOld> addEntity(AreaOld entity) {
        //ORecordId persisted = (ORecordId) app.getRepository().persist(entity);
        //entity.setId(persisted.getIdentity().toString());
        return new SkysailResponse<>(entity);
    }

}
