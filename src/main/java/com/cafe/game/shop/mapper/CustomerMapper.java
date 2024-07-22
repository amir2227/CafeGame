package com.cafe.game.shop.mapper;

import com.cafe.game.shop.dto.CustomerDto;
import com.cafe.game.shop.model.Customer;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    CustomerDto customerToDto(Customer customer);
    Customer dtoToCustomer(CustomerDto dto);
}
