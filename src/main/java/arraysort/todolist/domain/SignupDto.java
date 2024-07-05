package arraysort.todolist.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SignupDto {

    @NotBlank
    private String userId;

    @NotBlank
    private String userPassword;

    @NotBlank
    private String userName;

    public SignupDto() {

    }
    
    public void encodePassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
