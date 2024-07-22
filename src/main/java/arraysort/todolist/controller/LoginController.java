package arraysort.todolist.controller;

import arraysort.todolist.utils.UserUtil;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    // 기본 경로 로그인페이지 리다이렉트
    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    // 로그인 페이지
    @GetMapping("/login")
    public String login(Authentication authentication) {
        if (UserUtil.isAuthenticatedUser(authentication)) {
            return "redirect:/todo/list?page=1";
        }

        return "login";
    }
}
