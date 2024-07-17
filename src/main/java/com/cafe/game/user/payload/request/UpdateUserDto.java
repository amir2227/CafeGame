package com.cafe.game.user.payload.request;

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
public class UpdateUserDto {
    @NotNull
    @Size(min = 3, max = 255)
    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$")
    private String fullName;
}
