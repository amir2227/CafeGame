package com.cafe.game.shop.mapper;

import com.cafe.game.shop.dto.ShopDto;
import com.cafe.game.shop.model.Shop;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
@DecoratedWith(ShopMapperDecorator.class)
public interface ShopMapper {
    ShopDto shopToDto(Shop shop);
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Shop dtoToShop(ShopDto shopDto);
}
