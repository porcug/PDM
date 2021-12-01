package com.unitbv.backend.model.dto;

import com.unitbv.backend.model.enums.CommandStatus;
import com.unitbv.backend.validator.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private int id;
    @Positive(message = "The code of the order must be greater than 0!")
    private int code;
    @Positive(message = "The confirmed code of the order must be greater than 0!")
    private int confirmedCode;
    @NotNull(message = "The status of the order is null!")
    private CommandStatus status;
    private LocalDateTime createdDate;
    private LocalDateTime finishedDate;
    @Positive(message = "The total of the order must be greater than 0!")
    private double total;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @NotEmptyList
    private List<ProductDTO> products;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull(message = "The restaurant where the order was placed is null!")
    private RestaurantDTO restaurant;
    @NotNull(message = "The customer who placed the order is null!")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    private UserDTO client;
    @NotNull(message = "The delivery who took the order is null!")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "delivery_id", nullable = false)
    private UserDTO delivery;
    @NotNull(message = "The address of the order is null!")
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "address_id", nullable = false)
    private AddressDTO address;
}
