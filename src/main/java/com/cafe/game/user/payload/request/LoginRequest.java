package com.cafe.game.user.payload.request;


import com.ngn.auth.authservice.utils.annotations.ValidName;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginRequest {

    @NotEmpty(message = "username cannot be empty")
    @ValidName(message = "invalid username format")
    private String username;
    @NotNull
    @Size(min = 6,max = 64)
    private String password;
}
