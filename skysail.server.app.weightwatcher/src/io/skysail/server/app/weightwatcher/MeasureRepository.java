package io.skysail.server.app.weightwatcher;


import org.osgi.service.component.annotations.*;

import io.skysail.domain.core.repos.DbRepository;
import io.skysail.server.db.*;

@Component(immediate = true, property = "name=MeasuresRepository")
public class MeasureRepository extends GraphDbRepository<io.skysail.server.app.weightwatcher.Measure> implements DbRepository {

    @Reference
    public void setDbService(DbService dbService) {
        this.dbService = dbService;
    }

    public void unsetDbService(DbService dbService) {
        this.dbService = null;
    }

    @Activate
    public void activate() {
        //log.debug("activating io.skysail.server.app.weightwatcher.Measure" Repository);
        dbService.createWithSuperClass("V", DbClassName.of(io.skysail.server.app.weightwatcher.Measure.class));
        dbService.register(Measure.class);
    }

}