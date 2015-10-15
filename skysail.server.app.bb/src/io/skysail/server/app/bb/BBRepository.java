package io.skysail.server.app.bb;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import io.skysail.api.repos.DbRepository;
import io.skysail.server.db.DbService;
import io.skysail.server.db.GraphDbRepository;

@Component(immediate = true, property = "name=BodyboosterRepository")
public class BBRepository extends GraphDbRepository<Goal> implements DbRepository {

    @Reference
    public void setDbService(DbService dbService) {
        this.dbService = dbService;
    }

    public void unsetDbService(DbService dbService) {
        this.dbService = null;
    }

    @Activate
    public void activate() { // NO_UCD
        super.activate(Goal.class);
    }
}
