package io.skysail.server.app.bb;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import io.skysail.server.app.bb.achievements.Achievement;
import io.skysail.server.db.DbService;
import io.skysail.server.db.GraphDbRepository;
import io.skysail.server.repo.DbRepository;

@Component(immediate = true, property = "name=AchievementRepository")
public class AchievementRepository extends GraphDbRepository<Achievement> implements DbRepository {

    @Reference
    public void setDbService(DbService dbService) {
        this.dbService = dbService;
    }

    public void unsetDbService(DbService dbService) {
        this.dbService = null;
    }

    @Activate
    public void activate() { // NO_UCD
        dbService.createWithSuperClass("V", Achievement.class.getSimpleName());
        dbService.register(Achievement.class);
    }
}
