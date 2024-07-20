package arraysort.todolist.controller;

import arraysort.todolist.common.component.ImageComponent;
import arraysort.todolist.domain.ImageDto;
import arraysort.todolist.domain.SignupDto;
import arraysort.todolist.service.ImageService;
import arraysort.todolist.service.SignUpService;
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

    private final SignUpService signupService;
    private final ImageService imageService;
    private final ImageComponent imageComponent;

    @GetMapping

    public String signup() {
        return "signup";
    }

    @PostMapping
    public String userAdd(@Valid @ModelAttribute("user") SignupDto signupDto) {
        ImageDto image = imageComponent.uploadImage(signupDto.getUserId(), signupDto.getImageFile());
        signupService.addUser(signupDto);
        imageService.addImage(image);
        return "redirect:/login";
    }
}
