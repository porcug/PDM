package com.unitbv.backend.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "product")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "price", nullable = false)
    private double price;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @Column(name = "quantity_limit", nullable = false)
    private int quantityLimit;
    @Column(name = "available", nullable = false)
    private boolean available;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private RestaurantDO restaurant;
    @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
    private List<OrderDO> orders;
}
