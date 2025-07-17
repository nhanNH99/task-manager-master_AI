package pl.rengreen.taskmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.rengreen.taskmanager.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String listUsers(Model model, SecurityContextHolderAwareRequestWrapper request) {
        boolean isAdminSigned = request.isUserInRole("ROLE_ADMIN");

        model.addAttribute("users", userService.findAll());
        model.addAttribute("isAdminSigned", isAdminSigned);
        return "views/users";
    }

    @GetMapping("user/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping("/user/change-password")
    public String showChangePasswordForm() {
        return "views/change-password";
    }

    @PostMapping("/user/change-password")
    public String changePassword(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam String currentPassword,
            @RequestParam String newPassword,
            @RequestParam String confirmPassword,
            Model model) {

        String error = userService.changePassword(
                userDetails.getUsername(),
                currentPassword,
                newPassword,
                confirmPassword
        );
        if (error != null) {
            model.addAttribute("error", error);
        } else {
            model.addAttribute("success", "Password changed successfully.");
        }
        return "user/change-password";
    }

}
