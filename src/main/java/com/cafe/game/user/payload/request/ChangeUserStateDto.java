package com.cafe.game.user.payload.request;

import com.ngn.auth.authservice.enums.UserState;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChangeUserStateDto {
    @NotNull
    private UserState state;
}
