package com.bellitegrator.springmvc.services;

import com.bellitegrator.springmvc.forms.UserForm;
import com.bellitegrator.springmvc.models.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UsersService {

    User getUser(Integer userId);

    List<User> getAllUsers();

    void updateUser(User user, UserForm userForm);

    User loadUserByUsername(String email) throws UsernameNotFoundException;

    User getAuthenticatedUser();
}
