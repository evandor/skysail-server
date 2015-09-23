package io.skysail.server.app.bb;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import io.skysail.server.db.DbService;
import io.skysail.server.db.GraphDbRepository;
import io.skysail.server.repo.DbRepository;

@Component(immediate = true, property = "name=BodyboosterRepository")
public class Repository extends GraphDbRepository<Goal> implements DbRepository {

    @Reference
    public void setDbService(DbService dbService) {
        this.dbService = dbService;
    }

    public void unsetDbService(DbService dbService) {
        this.dbService = null;
    }

    @Activate
    public void activate() { // NO_UCD
        dbService.createWithSuperClass("V", Goal.class.getSimpleName());
        dbService.register(Goal.class);
    }
    
//    public List<Area> findAll(Class<?> cls, Filter filter, Pagination pagination) {
//        String sql = "SELECT from " + cls.getSimpleName() + " WHERE "+filter.getPreparedStatement();//+" ORDER BY name ";
////                + limitClause(pagination);
//        return dbService.findObjects(sql, filter.getParams());
//    }
//
//    public void persist(Area entity) {
//        return ;//dbService.persist(entity, edges);
//    }
    
    

}
