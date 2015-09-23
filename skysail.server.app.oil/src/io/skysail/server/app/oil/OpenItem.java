package io.skysail.server.app.oil;

import java.io.Serializable;

import javax.persistence.Id;

import io.skysail.api.domain.Identifiable;
import io.skysail.api.forms.Field;
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
