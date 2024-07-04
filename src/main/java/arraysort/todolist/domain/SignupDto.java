package arraysort.todolist.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SignupDto {

    @NotBlank(message = "아이디는 필수로 입력해야 합니다.")
    private String userId;

    @NotBlank(message = "비밀번호는 필수로 입력해야 합니다.")
    private String userPassword;

    @NotBlank(message = "닉네임은 필수로 입력해야 합니다.")
    private String userName;

    public SignupDto() {

    }

}
