package com.cafe.game.user.payload.response;

import com.cafe.game.user.enums.UserState;
import com.cafe.game.user.model.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Date;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private UUID id;
    private String username;
    private String phone;
    private String email;
    private String fullName;
    private Boolean hasImage;
    private Boolean isActive;
    private Boolean isPasswordChanged;
    private UserState userState;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private Set<Role> roles;
}
