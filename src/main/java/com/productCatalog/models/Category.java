package com.productCatalog.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity(name="categories")
public class Category extends BaseModel{
    String title;

    @OneToMany(fetch=jakarta.persistence.FetchType.LAZY,mappedBy ="category",cascade = CascadeType.REMOVE)
    List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
