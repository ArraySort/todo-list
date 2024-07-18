package arraysort.todolist.controller;

import arraysort.todolist.utils.UserUtil;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Authentication authentication) {
        if (UserUtil.isAuthenticatedUser(authentication)) {
            return "redirect:/todo/list?page=1";
        }
        
        return "login";
    }
}
