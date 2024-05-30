package com.bellitegrator.springmvc.services;

import com.bellitegrator.springmvc.forms.SignUpForm;

public interface SignUpService {

    void signUpUser(SignUpForm form);

    boolean isUserExist(String email);
}
