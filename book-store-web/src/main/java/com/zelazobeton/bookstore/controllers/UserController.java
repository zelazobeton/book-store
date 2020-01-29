package com.zelazobeton.bookstore.controllers;

import com.zelazobeton.bookstore.Templates;
import com.zelazobeton.bookstore.commands.CartItemCommand;
import com.zelazobeton.bookstore.commands.UserCommand;
import com.zelazobeton.bookstore.model.User;
import com.zelazobeton.bookstore.services.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model,
                           @AuthenticationPrincipal User user){
        if(user != null){
            return "redirect:/";
        }
        model.addAttribute("userCommand", new UserCommand());
        return Templates.REGISTER;
    }

    @PostMapping("/register")
    public String register(Model model,
                           @Valid @ModelAttribute UserCommand userCommand,
                           BindingResult result,
                           @AuthenticationPrincipal User user){
        if(result.hasErrors()) {
            result.getAllErrors().forEach(System.out::println);
            return Templates.REGISTER;
        }
        if(userService.userExists(userCommand)){
            result.rejectValue("username", "Email", "User already exists");
            result.getAllErrors().forEach(System.out::println);
            return Templates.REGISTER;
        }
        userService.registerUser(userCommand);
        return "redirect:/login";
    }
}
