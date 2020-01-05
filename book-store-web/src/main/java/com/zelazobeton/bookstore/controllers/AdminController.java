package com.zelazobeton.bookstore.controllers;

import com.zelazobeton.bookstore.services.interfaces.IAdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final IAdminService adminService;


    public AdminController(IAdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/console")
    public String getConsole(){
        return "console";
    }
}
