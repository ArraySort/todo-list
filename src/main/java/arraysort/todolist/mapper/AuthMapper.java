package arraysort.todolist.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthMapper {

    String selectUserNameByUserId(String userId);
}
