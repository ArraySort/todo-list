package arraysort.todolist.mapper;

import arraysort.todolist.domain.UserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SignupMapper {
    
    void insertUser(UserVO signupUser);

    int checkUser(UserVO signupUser);
}
