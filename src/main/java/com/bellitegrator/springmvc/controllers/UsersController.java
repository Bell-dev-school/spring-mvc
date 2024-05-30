package com.bellitegrator.springmvc.controllers;

import com.bellitegrator.springmvc.forms.UserForm;
import com.bellitegrator.springmvc.models.User;
import com.bellitegrator.springmvc.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping("/")
    public String getHomePage(){
        return "index";
    }

    @GetMapping("/users")
    public String getUsersPage(Model model) {
        List<User> users = usersService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/c_profile")
    public String getCustomerPage(Model model) {
        model.addAttribute("user", usersService.getAuthenticatedUser());
        return "customer_profile";
    }

    @PostMapping("/c_profile")
    public String updateCustomer(UserForm userForm) {
        User user = usersService.getAuthenticatedUser();
        usersService.updateUser(user, userForm);
        return "redirect:/c_profile";
    }

    @GetMapping("/d_profile")
    public String getDeliverymanPage(Model model) {
        model.addAttribute("user", usersService.getAuthenticatedUser());
        return "deliveryman_profile";
    }

    @PostMapping("/d_profile")
    public String updateDeliveryman(UserForm userForm) {
        User user = usersService.getAuthenticatedUser();
        usersService.updateUser(user, userForm);
        return "redirect:/d_profile";
    }

}

