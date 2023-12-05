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
public class Orders {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    private String phone;

    private String address;

    private Boolean status;

    private double total;

    public Orders(User user, String phone, String address, Boolean status, double total) {
        this.user = user;
        this.phone = phone;
        this.address = address;
        this.status = status;
        this.total = total;
    }
}
