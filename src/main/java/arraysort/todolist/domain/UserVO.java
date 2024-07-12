package arraysort.todolist.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserVO {

    private String userId;

    private String userPassword;

    private String userName;

    public static UserVO of(SignupDto signupDto) {
        return UserVO.builder()
                .userId(signupDto.getUserId())
                .userPassword(signupDto.getUserPassword())
                .userName(signupDto.getUserName())
                .build();
    }
}
