package org.launchcode.javawebdevtechjobspersistent.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Employer extends AbstractEntity {

    @NotNull
    @Size(min=3, max=50)
    private String location;

    // Initialize the id and value fields.
    public Employer(String aLocation) {
        super();
        this.location = aLocation;
    }

    public Employer() {
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
