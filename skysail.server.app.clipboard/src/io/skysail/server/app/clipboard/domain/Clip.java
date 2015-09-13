package io.skysail.server.app.clipboard.domain;

import io.skysail.api.domain.Identifiable;
import io.skysail.api.forms.Field;
import io.skysail.api.forms.HtmlPolicy;
import io.skysail.api.forms.InputType;
import io.skysail.server.forms.ListView;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@SuppressWarnings("serial")
@Getter
@Setter
@JsonIgnoreProperties("handler")
public class Clip implements Serializable, Identifiable {

    @Id
    private String id;

    @Field(inputType = InputType.TEXTAREA, htmlPolicy = HtmlPolicy.DEFAULT_HTML)
    @Size(min = 2, message = "Sorry, the content must have at least two Characters.")
    private String content;

    @Field(inputType = InputType.TAGS, htmlPolicy = HtmlPolicy.NO_HTML)
    private String tags;

    @Field(inputType = InputType.READONLY)
    private Date created;

    @Field(inputType = InputType.READONLY)
    private Date modified;

    @Field(inputType = InputType.READONLY)
    @ListView(hide = true)
    private String owner;

    public Clip() {
        this.created = new Date();
    }

}
