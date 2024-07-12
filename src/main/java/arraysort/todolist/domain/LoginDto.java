package arraysort.todolist.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginDto {

    @NotBlank
    private String userId;

    @NotBlank
    private String userPassword;

}
