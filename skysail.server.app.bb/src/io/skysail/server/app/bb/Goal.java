package io.skysail.server.app.bb;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import io.skysail.api.domain.Identifiable;
import io.skysail.api.forms.Field;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Goal implements Identifiable {

	@Id
	private String id;
	
	@Field
	@NotNull
	private String name;
}
