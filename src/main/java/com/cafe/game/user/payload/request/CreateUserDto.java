package com.cafe.game.user.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    @NotBlank
    @Size(min = 10,max = 10)
    @Pattern(regexp = "[0-9]{10}", message = "invalid nationalId")
    private String nationalId;
    @NotBlank
    @Pattern(regexp = "[0-9]{10,15}",message = "invalid phone number")
    private String phone;
    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$")
    private String fullName;
    @NotNull
    private Long facilityId;
}
