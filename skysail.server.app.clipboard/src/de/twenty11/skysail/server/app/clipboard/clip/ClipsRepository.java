package de.twenty11.skysail.server.app.clipboard.clip;

import io.skysail.server.db.DbService2;

import java.util.List;

import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;

import de.twenty11.skysail.server.um.domain.SkysailUser;

public class ClipsRepository {

    private DbService2 dbService;

    public ClipsRepository(DbService2 dbService) {
        this.dbService = dbService;
    }

    public Clip getById(String id) {
        return null;//dbService.find(id, Clip.class);
    }

    public String getByIdAsJson(String id) {
        return null;//dbService.findAndReturnJson(id, Clip.class);
    }

    public Clip add(Clip entity) {
        return null;//dbService.persist(entity);
    }

    public void update(Clip entity) {
        //dbService.update(entity);
    }

    public List<Clip> getClips(int page, Object rid, int linesPerPage) {
        return null;//dbService.query(new OSQLSynchQuery<Clip>("select * from Clip WHERE " + getWhereClause(rid)
//                + " ORDER BY content SKIP " + (linesPerPage * (page - 1)) + " LIMIT 10"), Clip.class);
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
