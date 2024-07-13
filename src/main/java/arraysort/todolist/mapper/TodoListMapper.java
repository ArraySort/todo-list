package arraysort.todolist.mapper;

import arraysort.todolist.domain.TodoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TodoListMapper {

    void createTodo(TodoVO todoVO);

    List<TodoVO> getListByUserId(String userId);

    TodoVO getDetailByTodoId(int todoId);

    void updateTodo(@Param("todoId") int todoId, @Param("updateTodoVO") TodoVO todoVO);
}
