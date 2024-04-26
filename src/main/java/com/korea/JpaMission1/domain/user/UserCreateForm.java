package com.korea.JpaMission1.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {
    @NotEmpty(message = "필수 입력항목입니다")
    private String username;

    @NotEmpty (message = "필수 입력항목입니다")
    private String password1;

    @NotEmpty (message = "필수 입력항목입니다")
    private String password2;

    @NotEmpty (message = "필수 입력항목입니다")
    @Email
    private String email;

}
