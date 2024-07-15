package arraysort.todolist.mapper;

import arraysort.todolist.domain.TodoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TodoListMapper {

    void insertTodo(TodoVO todoVO);

    List<TodoVO> selectTodoListByUserId(String userId);

    Optional<TodoVO> selectTodoDetailByTodoId(int todoId);

    void updateTodo(@Param("todoId") int todoId, @Param("updateTodoVO") TodoVO todoVO);

    void deleteTodo(int todoId);
}
