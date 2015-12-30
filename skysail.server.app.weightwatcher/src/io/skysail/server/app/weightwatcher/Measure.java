package io.skysail.server.app.weightwatcher;

import javax.persistence.Id;

import io.skysail.domain.Identifiable;
import io.skysail.domain.html.Field;
import io.skysail.domain.html.HtmlPolicy;
import io.skysail.domain.html.InputType;

public class Measure implements Identifiable {

    @Id
    private String id;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    // --- fields ---

    @Field(inputType = InputType.TEXT, htmlPolicy = HtmlPolicy.NO_HTML)
    private String weight;

    public void setWeight(String value) {
        this.weight = value;
    }

    public String getWeight() {
        return this.weight;
    }

    @Field(inputType = InputType.DATE, htmlPolicy = HtmlPolicy.NO_HTML)
    private String day;

    public void setDay(String value) {
        this.day = value;
    }

    public String getDay() {
        return this.day;
    }




}