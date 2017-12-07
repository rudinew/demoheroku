package com.example.demo.backend;



import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Довідник Країни
 */
@Entity
@Table(name="country")
public class Country extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private String name;

    public Country() {
    }

    public Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
