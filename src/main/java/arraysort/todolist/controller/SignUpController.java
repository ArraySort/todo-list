package arraysort.todolist.controller;

import arraysort.todolist.domain.SignupDto;
import arraysort.todolist.service.SignupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SignUpController {

    private final SignupService signupService;

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new SignupDto());
        return "signup";
    }

    @PostMapping("/signup")
    public String createUserController(@Valid @ModelAttribute("user") SignupDto signupDto) {
        signupService.createUserService(signupDto);
        return "redirect:/login";
    }
}
