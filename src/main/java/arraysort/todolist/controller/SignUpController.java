package arraysort.todolist.controller;

import arraysort.todolist.domain.SignupDto;
import arraysort.todolist.service.SignUpService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/signup")
public class SignUpController {

    private final SignUpService signupService;

    // 회원가입 페이지
    @GetMapping
    public String signup() {
        return "signup";
    }

    // 회원가입 요청
    @PostMapping
    public String userAdd(@Valid @ModelAttribute("user") SignupDto signupDto, Model model) {
        signupService.addUser(signupDto);

        model.addAttribute("message", "회원가입이 정상적으로 처리되었습니다.");
        model.addAttribute("url", "SIGNUP");
        return "common/alert";
    }
}
