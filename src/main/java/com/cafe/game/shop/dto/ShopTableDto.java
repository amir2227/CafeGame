package com.cafe.game.shop.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ShopTableDto {
    private UUID id;
    private Integer number;
    private String title;
    private Boolean isBusy;
    private Boolean active;
}
