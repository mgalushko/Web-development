package com.store.webstore.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id_item;
    private String name;
    private Integer price;

    public Integer getId_item() {
        return id_item;
    }

    public void setId_item(Integer id_item) {
        this.id_item = id_item;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
