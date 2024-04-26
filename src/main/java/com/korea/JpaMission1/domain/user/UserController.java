package com.korea.JpaMission1.domain.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/signup")
    public String signupForm(UserCreateForm userCreateForm){
        return "signup_form";
    }

    @PostMapping("/signup")
    public String signup(@Valid UserCreateForm userCreateForm,
                         BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "signup_form";
        }
        if(!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())){
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 비밀번호가 일치하지않습니다.");
            model.addAttribute("error", "2개의 비밀번호가 일치하지않습니다.");
            return "signup_form";
        }
        if(userService.findByEmail(userCreateForm.getEmail())!=null){
            model.addAttribute("error", "이미 존재하는 이메일 입니다.");
            return "signup_form";
        }
        userService.create(userCreateForm.getUsername(), userCreateForm.getPassword1(),
                userCreateForm.getEmail());
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(){
        return "login_form";
    }
}
