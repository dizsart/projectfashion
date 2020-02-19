package com.assignment.projectfashion;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Entity (name = "categories")
@Table(name = "categories")
public class Categories implements Serializable
{

    public Categories() {
    }

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fashion_categories")
    private String fashionCategories;


    public Categories (Long id, String fashionCategories) {
        this.id = id;
        this.fashionCategories = fashionCategories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFashionCategories() {
        return fashionCategories;
    }

    public void setFashionCategories(String fashionCategories) {
        this.fashionCategories = fashionCategories;
    }



}