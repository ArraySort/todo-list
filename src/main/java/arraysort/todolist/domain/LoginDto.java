package arraysort.todolist.domain;

import lombok.Getter;

@Getter
public class LoginDto {

    private String userId;

    private String userPassword;

    public LoginDto(String userId, String userPassword) {
        this.userId = userId;
        this.userPassword = userPassword;
    }
}
