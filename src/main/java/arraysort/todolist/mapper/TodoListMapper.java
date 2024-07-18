package arraysort.todolist.mapper;

import arraysort.todolist.domain.TodoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TodoListMapper {

    void insertTodo(TodoVO todoVO);

    List<TodoVO> selectTodoListByUserId(String userId, boolean todoDone, int rowCount, int offset);

    Optional<TodoVO> selectTodoDetailByTodoId(long todoId, String userId);

    void updateTodo(long todoId, TodoVO todoVO);

    void deleteTodo(long todoId);

    Optional<Integer> selectExistTodoId(long todoId, String userId);

    int selectTotalCount(String userId, boolean todoDone);

    void updateTodoDone(List<Long> todoIds);
}
