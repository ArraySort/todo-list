package arraysort.todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @PostMapping("/login-process")
    public String loginProcess() {
        return "login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
