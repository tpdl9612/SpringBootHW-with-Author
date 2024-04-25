package com.korea.JpaMission1.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {

    @NotEmpty(message = "사용자명은 필수입니다.")
    private String username;

    @NotEmpty(message = "비밀번호는 필수입니다.")
    private String password1;
    @NotEmpty(message = "비밀번호확인은 필수입니다.")
    private String password2;
    @NotEmpty(message = "이메일은 필수입니다.")
    @Email
    private String email;
}
