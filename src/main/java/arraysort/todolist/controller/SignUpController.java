package arraysort.todolist.controller;

import arraysort.todolist.domain.SignupDto;
import arraysort.todolist.service.SignupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String createUser(@Valid @ModelAttribute("user") SignupDto signupDto, BindingResult bindingResult) {

        if (signupService.checkUser(signupDto) != 0) {
            bindingResult.rejectValue("userId", "Check");
            return "signup";
        }

        if (bindingResult.hasErrors()) {
            return "signup";
        }

        signupService.createUser(signupDto);
        return "login";
    }
}
