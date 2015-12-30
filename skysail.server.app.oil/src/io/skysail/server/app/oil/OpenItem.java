package io.skysail.server.app.oil;

import java.io.Serializable;

import javax.persistence.Id;

import io.skysail.domain.Identifiable;
import io.skysail.domain.html.Field;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpenItem implements Identifiable, Serializable {

	@Id
	private String id;
	
	@Field
	private String title;
	
	private Topic topic;
}
