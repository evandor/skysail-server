package io.skysail.server.app.bb;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

import io.skysail.server.db.DbRepository;
import io.skysail.server.db.DbService;
import io.skysail.server.queryfilter.Filter;
import io.skysail.server.queryfilter.pagination.Pagination;

@Component(immediate = true, property = "name=BodyboosterRepository")
public class Repository implements DbRepository {

    @Reference(cardinality = ReferenceCardinality.OPTIONAL)
    private DbService dbService;
    
    public List<Area> findAll(Class<?> cls, Filter filter, Pagination pagination) {
        String sql = "SELECT from " + cls.getSimpleName() + " WHERE "+filter.getPreparedStatement();//+" ORDER BY name ";
//                + limitClause(pagination);
        return dbService.findObjects(sql, filter.getParams());
    }
    
    

}
