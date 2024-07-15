package arraysort.todolist.mapper;

import arraysort.todolist.domain.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface LoginMapper {

    Optional<UserVO> selectUserById(String userId);
}
