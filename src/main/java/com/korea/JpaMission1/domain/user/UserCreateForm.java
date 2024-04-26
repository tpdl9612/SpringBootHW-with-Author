package com.korea.JpaMission1.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {
    @NotEmpty(message = "가입 시 필수 항목입니다.")
    @Size(min = 2, max = 10)
    private String username;

    @NotEmpty(message = "가입 시 필수 항목입니다.")
    @Size(min = 4, max = 20)
    private String password1;
    @NotEmpty(message = "가입 시 필수 항목입니다.")
    @Size(min = 4, max = 20)
    private String password2;

    @NotEmpty(message = "가입 시 필수 항목입니다.")
    @Email
    @Size(min = 4, max = 20)
    private String email;
}
