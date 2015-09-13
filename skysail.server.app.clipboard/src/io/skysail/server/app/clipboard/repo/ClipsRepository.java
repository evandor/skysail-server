package io.skysail.server.app.clipboard.repo;

import io.skysail.server.app.clipboard.domain.Clip;
import io.skysail.server.db.DbRepository;
import io.skysail.server.db.DbService;

import java.util.HashMap;
import java.util.List;

import aQute.bnd.annotation.component.Activate;
import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;

@Component(immediate = true, properties = "name=ClipsRepository")
public class ClipsRepository implements DbRepository {

    private static DbService dbService;

    @Activate
    public void activate() {
        dbService.createWithSuperClass("V", Clip.class.getSimpleName());
        dbService.register(Clip.class);
        //dbService.createUniqueIndex(Clip.class, "name", "owner");
    }

    @Reference
    public void setDbService(DbService dbService) {
        ClipsRepository.dbService = dbService;
    }

    public void unsetDbService(DbService dbService) {
        ClipsRepository.dbService = null;
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

    @SuppressWarnings({ "unchecked", "serial" })
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
}
