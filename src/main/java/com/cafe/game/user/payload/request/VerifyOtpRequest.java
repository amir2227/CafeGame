package com.cafe.game.user.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VerifyOtpRequest {
    @NotBlank
    @Size(max = 63)
    private String token;
    @NotBlank
    @Size(min = 5,max = 5)
    private String code;
}
