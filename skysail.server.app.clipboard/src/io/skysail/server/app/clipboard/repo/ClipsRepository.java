package io.skysail.server.app.clipboard.repo;

import java.util.HashMap;
import java.util.List;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.log.LogService;

import io.skysail.domain.Identifiable;
import io.skysail.domain.core.ApplicationModel;
import io.skysail.domain.core.repos.DbRepository;
import io.skysail.server.app.clipboard.domain.Clip;
import io.skysail.server.db.DbService;

@Component(immediate = true, property = "name=ClipsRepository")
public class ClipsRepository implements DbRepository {

	@Reference
    private DbService dbService;
	
	@Reference
	private LogService logService;

    @Activate
    public void activate() {
        //super.ac
        dbService.createWithSuperClass("V", Clip.class.getSimpleName());
        dbService.register(Clip.class);
        //dbService.createUniqueIndex(Clip.class, "name", "owner");
    }

    public Clip getById(String id) {
        return null;//dbService.find(id, Clip.class);
    }

    public String getByIdAsJson(String id) {
        return null;//dbService.findAndReturnJson(id, Clip.class);
    }

    public Object add(Clip entity) {
        return dbService.persist(entity);
    }

    public void update(Clip entity) {
        //dbService.update(entity);
    }

    @SuppressWarnings({ "serial" })
    public List<Clip> getClips(int page, Object rid, int linesPerPage) {
        String sql = "select * from Clip WHERE owner=:owner ORDER BY content SKIP " + (linesPerPage * (page - 1)) + " LIMIT 10";
        return dbService.findObjects(sql,new HashMap<String,Object>() {{
            put("owner", rid);
        }});
    }

    public List<String> getClipsAsJson() {
        return null;//dbService.findAllAsJsonList(Clip.class, SkysailUser.class);
    }

    public void delete(String id) {
        //dbService.delete(id);
    }

    public long getClipCount(Object rid) {
        return 0;//null;//
//        dbService
//                .query(new OSQLSynchQuery<ODocument>("select COUNT(*) as count from Clip WHERE " + getWhereClause(rid)),
//                        Clip.class).get(0).field("count");
    }

    private String getWhereClause(Object rid) {

        return "owner=" + rid;
    }

	public Object save(Identifiable identifiable) {
		return null;
	}

	public Object update(String id, Object entity, String... edges) {
		return null;
	}

    @Override
    public Identifiable findOne(String id) {
        return null;
    }

    @Override
    public Class<? extends Identifiable> getRootEntity() {
        return null;
    }

    @Override
    public Object save(Identifiable identifiable, ApplicationModel applicationModel) {
        return null;
    }

    @Override
    public Object update(String id, Identifiable entity, String... edges) {
        return null;
    }

    @Override
    public void delete(Identifiable identifiable) {
    }
}
