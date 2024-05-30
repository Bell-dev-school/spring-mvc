package com.bellitegrator.springmvc.controllers;

import com.bellitegrator.springmvc.forms.SignUpForm;
import com.bellitegrator.springmvc.services.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/signup")
public class SignUpController {

    private final SignUpService signUpService;

    @GetMapping
    public String getSignUpPage() {
        return "sign_up";
    }

    @PostMapping
    public String signUpUser(SignUpForm form) {
        String email = form.getEmail();
        if(signUpService.isUserExist(email)) {
            return "redirect:/signup";
        }
        signUpService.signUpUser(form);
        return "redirect:/signin";
    }

}

