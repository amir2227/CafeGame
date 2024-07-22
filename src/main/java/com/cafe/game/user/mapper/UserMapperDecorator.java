package com.cafe.game.user.mapper;

import com.cafe.game.user.model.User;
import com.cafe.game.user.payload.request.CreateUserDto;
import com.cafe.game.user.payload.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class UserMapperDecorator implements UserMapper{

    @Autowired
    @Qualifier("delegate")
    private UserMapper delegate;

    @Override
    public UserResponse userToResponse(User user) {
        return delegate.userToResponse(user);
    }

    @Override
    public User dtoToUser(CreateUserDto dto) {
        return delegate.dtoToUser(dto);
    }
}
