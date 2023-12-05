package com.helios.commerce.dao;

import com.helios.commerce.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RevenueDAO {

    private Product product;
    private int quantity;

    private double total;


}
