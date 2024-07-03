package arraysort.todolist.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserVO {

    private String userId;

    private String userPassword;

    private String userName;

}
