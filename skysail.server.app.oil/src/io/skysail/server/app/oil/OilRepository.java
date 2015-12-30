package io.skysail.server.app.oil;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import io.skysail.domain.core.repos.DbRepository;
import io.skysail.server.db.DbService;
import io.skysail.server.db.GraphDbRepository;

@Component(immediate = true, property = "name=OilRepository")
public class OilRepository extends GraphDbRepository<OpenItem>implements DbRepository {

	@Reference
	public void setDbService(DbService dbService) {
		this.dbService = dbService;
	}

	public void unsetDbService(DbService dbService) {
		this.dbService = null;
	}

	@Activate
	public void activate() { 
		dbService.createWithSuperClass("V", OpenItem.class.getSimpleName());
		dbService.register(OpenItem.class);
	}

}
