package arraysort.todolist.controller;

import arraysort.todolist.domain.SignupDto;
import arraysort.todolist.service.SignupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SignUpController {

    private final SignupService signupService;

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String createUser(@ModelAttribute SignupDto signupDto) {
        log.info("post : signup={}", signupDto);
        signupService.createUser(signupDto);
        return "login";
    }
}
