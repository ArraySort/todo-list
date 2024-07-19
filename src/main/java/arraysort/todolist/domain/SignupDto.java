package arraysort.todolist.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
public class SignupDto {

    @NotBlank
    @Size(min = 4, max = 20, message = "아이디는 최소 4글자, 최대 20글자여야 합니다.")
    private String userId;

    @NotBlank
    private String userPassword;

    @NotBlank
    @Size(min = 2, max = 20, message = "닉네임은 최소 2글자, 최대 20글자여야 합니다.")
    private String userName;

    private MultipartFile imageFile;

    public void encodePassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
