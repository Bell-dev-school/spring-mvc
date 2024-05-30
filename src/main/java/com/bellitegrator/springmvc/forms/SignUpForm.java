package com.bellitegrator.springmvc.forms;

import com.bellitegrator.springmvc.models.User;
import lombok.Data;

@Data
public class SignUpForm {
    private String name;
    private String surname;
    private String telNumber;
    private String email;
    private String password;
    private User.Role role;
}
