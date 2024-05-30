package com.bellitegrator.springmvc.forms;

import lombok.Data;



@Data
public class UserForm {
    private String name;
    private String surname;
    private String telNumber;
    private String email;
    private String password;
}

