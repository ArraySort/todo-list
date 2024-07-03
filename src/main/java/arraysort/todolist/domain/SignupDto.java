package arraysort.todolist.domain;

import lombok.Data;

@Data
public class SignupDto {
    private String userId;
    private String userPassword;
    private String userName;
    // TODO : 유저 프로필 사진 업로드
}
