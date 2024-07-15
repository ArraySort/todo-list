package arraysort.todolist.controller;

import arraysort.todolist.domain.SignupDto;
import arraysort.todolist.service.SignupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/signup")
public class SignUpController {

    private final SignupService signupService;

    @GetMapping
    public String signup() {
        return "signup";
    }

    @PostMapping
    public String userAdd(@Valid @ModelAttribute("user") SignupDto signupDto) {
        signupService.addUser(signupDto);
        return "redirect:/login";
    }
}
