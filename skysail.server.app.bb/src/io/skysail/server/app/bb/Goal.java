package io.skysail.server.app.bb;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.skysail.api.domain.Identifiable;
import io.skysail.api.forms.Field;
import io.skysail.server.app.bb.areas.Area;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Goal implements Identifiable {

    @Id
    private String id;

    @Field
    @NotNull
    @Size(min = 2)
    private String name;

    private Area area;
    
    public Goal(String name, Area area) {
        this.name = name;
        this.area = area;
    }

}
