package io.skysail.server.app.oil;

import java.io.Serializable;

import javax.persistence.Id;

import io.skysail.domain.Identifiable;
import io.skysail.domain.html.Field;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Topic implements Identifiable, Serializable {


	@Id
	private String id;
	
	@Field
	private String name;
	
	public Topic(String name) {
		this.name = name;
	}
}
