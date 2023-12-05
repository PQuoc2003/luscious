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
public class Cart {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    public Cart(User user, Product product,int quantity ,boolean status) {
        this.user = user;
        this.product = product;
        this.quantity = quantity;
        this.status = status;
    }

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private boolean status;
    @Column
    private int quantity;



}
