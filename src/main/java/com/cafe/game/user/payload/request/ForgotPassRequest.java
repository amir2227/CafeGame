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
public class ForgotPassRequest extends VerifyOtpRequest{
    @NotBlank
    @Size(min = 6,max = 63)
    private String newPassword;
}
