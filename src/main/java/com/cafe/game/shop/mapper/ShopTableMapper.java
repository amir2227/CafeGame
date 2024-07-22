package com.cafe.game.shop.mapper;

import com.cafe.game.shop.dto.ShopTableDto;
import com.cafe.game.shop.model.ShopTable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ShopTableMapper {
    ShopTableDto shopTableToDto(ShopTable shopTable);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ShopTable dtoToShopTable(ShopTableDto dto);
}
