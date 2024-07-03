package arraysort.todolist.mapper;

import arraysort.todolist.domain.SignupDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SignupMapper {
    void insert(SignupDto signupDto);
}
