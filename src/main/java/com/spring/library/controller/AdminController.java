package com.spring.library.controller;

import com.spring.library.entity.User;
import com.spring.library.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * admin page; list users, changes a users role
     * @param model
     * @return
     */
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String adminPage(Model model) {
        adminService.adminPage(model);
        return "admin";
    }

    @PostMapping("admin/changeRole")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String changeRole(User user, RedirectAttributes redirectAttributes) {
        adminService.changeRole(user, redirectAttributes);
        return "redirect:/admin";
    }
}
