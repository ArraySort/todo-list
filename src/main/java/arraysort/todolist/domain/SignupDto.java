package arraysort.todolist.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SignupDto {

    @NotBlank(message = "필수 입력값입니다.")
    private String userId;

    @NotBlank(message = "필수 입력값입니다.")
    private String userPassword;

    @NotBlank(message = "필수 입력값입니다.")
    private String userName;

    public SignupDto() {

    }

    public void encodePassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
