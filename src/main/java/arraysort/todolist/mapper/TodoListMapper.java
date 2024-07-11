package arraysort.todolist.mapper;

import arraysort.todolist.domain.TodoVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TodoListMapper {

    void createTodo(TodoVO todoVO);
}
