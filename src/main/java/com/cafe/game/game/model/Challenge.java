package com.cafe.game.game.model;

import com.cafe.game.shop.model.Shop;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "challenge")
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 600)
    private String text;

    @Column(length = 100)
    private String img;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "shop_id")
    private Shop shop;



}
