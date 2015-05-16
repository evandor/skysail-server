package de.twenty11.skysail.server.app.clipboard.clip;

import io.skysail.api.forms.Field;
import io.skysail.api.forms.HtmlPolicy;
import io.skysail.api.forms.InputType;

import java.util.Date;

import javax.persistence.Id;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

import de.twenty11.skysail.server.um.domain.SkysailUser;

@Getter
@Setter
public class Clip {

	@Id
	private Object rid;
	
    @Field(type = InputType.TEXTAREA, htmlPolicy = HtmlPolicy.DEFAULT_HTML)
    @Size(min = 2, message = "Sorry, the content must have at least two Characters.")
    private String content;

    @Field(type = InputType.TAGS, htmlPolicy = HtmlPolicy.NO_HTML)
    private String tags;
        
    @Field(type = InputType.READONLY)
    private Date created;
    
    @Field(type = InputType.READONLY)
    private Date modified;

    @Field(type = InputType.READONLY)
    //@JsonIgnore
    private SkysailUser owner;
    
    public Clip() {
        this.created = new Date();
    }
    
}
