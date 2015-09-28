package io.skysail.server.app.bb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.skysail.api.domain.Identifiable;
import io.skysail.api.forms.Field;
import io.skysail.api.forms.InputType;
import io.skysail.server.app.bb.achievements.Achievement;
import io.skysail.server.app.bb.areas.Area;
import io.skysail.server.forms.ListView;
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

    @Field(inputType = InputType.TEXTAREA)
    @ListView(truncate = 60)
    private String remarks;
    
    @Field(inputType = InputType.DATE)
    private Date due;

    @Field
    private BigDecimal beginn = new BigDecimal(0);

    @Field
    private BigDecimal target = new BigDecimal(10);

    private Area area;

    private List<Achievement> achievements = new ArrayList<>();
    
    @Field(inputType = InputType.READONLY)
    @ListView(hide=true)
    private String owner = "#1";
    
    public Goal(String name, Area area) {
        this.name = name;
        this.area = area;
    }
}
