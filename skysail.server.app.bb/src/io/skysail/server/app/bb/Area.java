package io.skysail.server.app.bb;

import javax.persistence.Id;

import io.skysail.api.domain.Identifiable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Area implements Identifiable {

    @Id
    private String id;
    

}
