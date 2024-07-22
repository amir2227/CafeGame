package com.cafe.game.shop.dto;

import com.cafe.game.category.model.Category;
import com.cafe.game.shop.model.Address;
import com.cafe.game.shop.model.Image;
import com.cafe.game.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ShopDto {
    private UUID id;
    private String name;
    private Address address;
    private List<String> categories;
    private String description;
    private List<Image> images;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private List<ShopTableDto> shopTables;
}
