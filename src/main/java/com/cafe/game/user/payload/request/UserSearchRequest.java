package com.cafe.game.user.payload.request;

import com.ngn.auth.authservice.enums.UserState;
import com.ngn.auth.authservice.utils.annotations.ValidateString;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
public class UserSearchRequest extends SearchDto{

    private Boolean isActive;
    private Boolean isPasswordChanged;
    private UserState userState;
    @Pattern(regexp = "^[0-9\\s]+$")
    @Size(max = 13)
    private String phone;
    @Pattern(regexp = "^[0-9\\s]+$")
    @Size(max = 11)
    private String nationalId;
    @Min(0)
    private Integer page;
    @Min(10)
    @Max(30)
    private Integer size;
    @ValidateString(acceptedValues = {"createDate","updateDate","fullName","state","phone", "nationalId","isActive"})
    private String sortBy;
    @ValidateString(acceptedValues = {"ASC","DESC"})
    private String sortOrder;
}
