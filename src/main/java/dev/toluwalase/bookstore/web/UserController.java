package dev.toluwalase.bookstore.web;

import dev.toluwalase.bookstore.model.SignupForm;
import dev.toluwalase.bookstore.model.User;
import dev.toluwalase.bookstore.model.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("signupform", new SignupForm());
        return "signup";
    }

    @PostMapping({"/saveuser", "/signup"})
    public String saveUser(@ModelAttribute("signupform") SignupForm signupForm,
                           BindingResult bindingResult,
                           Model model) {
        String username = signupForm.getUsername() != null ? signupForm.getUsername().trim() : "";
        String password = signupForm.getPassword() != null ? signupForm.getPassword() : "";
        String passwordCheck = signupForm.getPasswordCheck() != null ? signupForm.getPasswordCheck() : "";
        String role = signupForm.getRole() != null ? signupForm.getRole().trim().toUpperCase() : "USER";

        if (!role.equals("ADMIN") && !role.equals("USER")) {
            role = "USER";
        }
        signupForm.setRole(role);

        if (username.isEmpty()) {
            bindingResult.rejectValue("username", "err.usernameEmpty", "Username cannot be empty");
            model.addAttribute("errorMessage", "Username cannot be empty.");
            return "signup";
        }

        if (password.isEmpty()) {
            bindingResult.rejectValue("password", "err.passwordEmpty", "Password cannot be empty");
            model.addAttribute("errorMessage", "Password cannot be empty.");
            return "signup";
        }

        if (!password.equals(passwordCheck)) {
            bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords do not match");
            model.addAttribute("errorMessage", "Passwords do not match.");
            return "signup";
        }

        if (userRepository.findByUsername(username) != null) {
            bindingResult.rejectValue("username", "err.usernameExists", "Username already exists");
            model.addAttribute("errorMessage", "Username already exists. Please choose another username.");
            return "signup";
        }

        // Save new user
        String passwordHash = passwordEncoder.encode(password);
        User newUser = new User(username, passwordHash, role);
        userRepository.save(newUser);

        return "redirect:/login?signupSuccess";
    }
}

