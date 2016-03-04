package io.skysail.server.app.bb.achievements;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Id;

import io.skysail.domain.Identifiable;
import io.skysail.domain.html.Field;
import io.skysail.domain.html.InputType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Achievement implements Identifiable {

    @Id
    private String id;
    
    @Field(inputType = InputType.DATE)
    private Date date;
    
    @Field
    private BigDecimal value;
}
