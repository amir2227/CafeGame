package com.cafe.game.user.payload.response;

import com.cafe.game.user.enums.UserState;
import com.cafe.game.user.model.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String nationalId;
    private String phone;
    private String fullName;
    private Boolean hasImage;
    private UUID uuid;
    private Boolean isActive;
    private Boolean isPasswordChanged;
    private UserState userState;
    private Date createDate;
    private Date updateDate;
    private Set<Role> roles;
}
