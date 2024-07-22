package com.cafe.game.shop.model;

import com.cafe.game.category.model.Category;
import com.cafe.game.user.model.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.time.OffsetDateTime;
import java.util.*;

@Data
@Entity
@Table(name = "shop")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;

    @Column(length = 60)
    private String name;
    @Embedded
    @Column(nullable = false)
    private Address address;
    @ManyToMany
    private Set<Category> categories = new HashSet<>();
    @Column
    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Image> images = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @CreationTimestamp
    @Column(columnDefinition = "timestamp")
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(columnDefinition = "timestamp")
    private OffsetDateTime updatedAt;
//
//    @OneToMany(mappedBy = "shop", cascade = {CascadeType.ALL})
//    private List<Puzzle> puzzles;
//    @OneToMany(mappedBy = "shop", cascade = {CascadeType.ALL})
//    private List<ShopCard> shopCards;

    @OneToMany(mappedBy = "shop",cascade = CascadeType.ALL)
    private List<ShopTable> shopTables = new ArrayList<>();

}
