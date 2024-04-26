package com.korea.JpaMission1.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SiteUser create(String username, String password, String email){
        SiteUser user = new SiteUser();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        this.userRepository.save(user);
        return user;
    }

    public SiteUser findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public String validateUserCreateForm(UserCreateForm userCreateForm, Model model) {
        // 사용자 이름 입력 여부 확인
        if (userCreateForm.getUsername().isEmpty()) {
            model.addAttribute("error", "사용자 이름은 필수 항목입니다.");
            return "signup_form";
        }
        // 이메일 입력 여부 확인
        if (userCreateForm.getEmail().isEmpty()) {
            model.addAttribute("error", "이메일은 필수 항목입니다.");
            return "signup_form";
        }
        // 비밀번호 입력 여부 확인
        if (userCreateForm.getPassword1().isEmpty()) {
            model.addAttribute("error", "비밀번호는 필수 항목입니다.");
            return "signup_form";
        }
        // 비밀번호 확인 입력 여부 확인
        if (userCreateForm.getPassword2().isEmpty()) {
            model.addAttribute("error", "비밀번호 확인은 필수 항목입니다.");
            return "signup_form";
        }
        return null; // 유효성 검사 통과
    }
}
