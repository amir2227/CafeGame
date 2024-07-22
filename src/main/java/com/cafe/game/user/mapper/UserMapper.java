package com.cafe.game.user.mapper;

import com.cafe.game.user.model.User;
import com.cafe.game.user.payload.request.CreateUserDto;
import com.cafe.game.user.payload.response.UserResponse;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
@DecoratedWith(UserMapperDecorator.class)
public interface UserMapper {
    UserResponse userToResponse(User user);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "password", ignore = true)
    User dtoToUser(CreateUserDto dto);
}
