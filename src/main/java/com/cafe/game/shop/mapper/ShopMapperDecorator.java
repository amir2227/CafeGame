package com.cafe.game.shop.mapper;

import com.cafe.game.shop.dto.ShopDto;
import com.cafe.game.shop.dto.ShopTableDto;
import com.cafe.game.shop.model.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;

public abstract class ShopMapperDecorator implements ShopMapper {

    @Autowired
    @Qualifier("delegate")
    private ShopMapper delegate;

    @Autowired
    private ShopTableMapper shopTableMapper;

    @Override
    public ShopDto shopToDto(Shop shop) {
        if (shop != null){
            ShopDto shopDto = delegate.shopToDto(shop);
            if (!shop.getCategories().isEmpty()){
                List<String> categories = new ArrayList<>();
                shop.getCategories().forEach(category ->
                    categories.add(category.getCode()));
                shopDto.setCategories(categories);
            }
            if (!shop.getShopTables().isEmpty()) {
                List<ShopTableDto> shopTableDtos = new ArrayList<>();
                shop.getShopTables().forEach(st -> {
                    ShopTableDto dto = shopTableMapper.shopTableToDto(st);
                    shopTableDtos.add(dto);
                });
                shopDto.setShopTables(shopTableDtos);
            }
        }
        return null;
    }

    @Override
    public Shop dtoToShop(ShopDto shopDto) {
        return delegate.dtoToShop(shopDto);
    }
}
