package com.cafe.game.shop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Address {

    @Column(length = 64,nullable = false)
    private String city;
    @Column(length = 64,nullable = false)
    private String state;
    @Column(length = 128)
    private String addressNumber;
    @Column(length = 512)
    private String address;
    @Column
    private Long latitude;
    @Column
    private Long longitude;

}
