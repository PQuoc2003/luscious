package com.helios.commerce.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;
    private String description;
    @Enumerated(EnumType.STRING)
    private Type type;

    public Product(String name, Double price, String description, Type type) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.type = type;
    }
}
