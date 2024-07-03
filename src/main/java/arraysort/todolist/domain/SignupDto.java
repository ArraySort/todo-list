package arraysort.todolist.domain;

import lombok.Getter;

@Getter
public class SignupDto {

    private String userId;

    private String userPassword;

    private String userName;

    public SignupDto(String userId, String userPassword, String userName) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
    }
}
