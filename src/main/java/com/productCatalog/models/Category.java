package com.productCatalog.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;



@Entity
public class Category {
    String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
