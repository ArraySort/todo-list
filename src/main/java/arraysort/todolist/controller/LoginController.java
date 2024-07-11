package arraysort.todolist.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("login-process")
    public String loginPrecess(HttpServletRequest request, Model model) {
        String loginError = (String) request.getSession().getAttribute("loginError");
        if (loginError != null) {
            model.addAttribute("loginError", loginError);
            request.getSession().removeAttribute("loginError");
        }
        return "login";
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
