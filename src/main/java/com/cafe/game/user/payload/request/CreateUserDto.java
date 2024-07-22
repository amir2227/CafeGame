package com.cafe.game.user.payload.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateUserDto {

    @NotBlank
    @Size(min = 6,max = 63)
    private String password;

    @Size(min = 3,max = 120)
    @Email
    private String email;

    @Pattern(regexp = "[0-9]{10,15}",message = "invalid phone number")
    private String phone;

    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$")
    private String fullName;
}
