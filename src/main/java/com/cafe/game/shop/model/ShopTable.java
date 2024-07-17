package com.cafe.game.shop.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class ShopTable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;

    @Column(nullable = false)
    private Integer number;
    @Column(length = 128)
    private String title;
    @ManyToOne
    private Shop shop;

    @Column(nullable = false)
    private Boolean isBusy;

    @Column(nullable = false)
    private Boolean active;

    @OneToMany(mappedBy = "shopTable")
    private List<Customer> customers = new ArrayList<>();
}
