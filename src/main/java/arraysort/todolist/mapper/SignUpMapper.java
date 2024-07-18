package arraysort.todolist.mapper;

import arraysort.todolist.domain.UserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SignUpMapper {

    void insertUser(UserVO signupUser);

    int selectUserCountById(String userId);
}
