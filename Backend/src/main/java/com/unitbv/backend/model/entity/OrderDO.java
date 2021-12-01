package com.unitbv.backend.model.entity;

import com.unitbv.backend.model.enums.CommandStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "order")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "code", nullable = false)
    private int code;
    @Column(name = "confirmedCode", nullable = false)
    private int confirmedCode;
    @Column(name = "status", nullable = false)
    private CommandStatus status;
    @Column(name = "createdDate", nullable = false)
    private LocalDateTime createdDate;
    @Column(name = "finishedDate", nullable = false)
    private LocalDateTime finishedDate;
    @Column(name = "total", nullable = false)
    private double total;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<ProductDO> products;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private RestaurantDO restaurant;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    private UserDO client;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "delivery_id", nullable = false)
    private UserDO delivery;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "address_id", nullable = false)
    private AddressDO address;
}
