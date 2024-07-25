package arraysort.todolist.mapper;

import arraysort.todolist.domain.TodoListQueryDto;
import arraysort.todolist.domain.TodoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TodoListMapper {

    void insertTodo(TodoVO todoVO);

    List<TodoVO> selectTodoListByUserId(TodoListQueryDto todoListQueryDto);

    Optional<TodoVO> selectTodoDetailByTodoId(long todoId, String userId);

    void updateTodo(long todoId, TodoVO todoVO);

    void deleteTodo(long todoId);

    Optional<Integer> selectExistTodoId(long todoId, String userId);

    int selectTotalCount(String userId, boolean todoDone, String searchTitle);

    void updateTodoDone(List<Long> todoIds);

    void updateTodoNotDone(List<Long> todoIds);

    void deleteCheckedTodos(List<Long> todoIds);

    List<Long> selectExistTodoIdList(List<Long> todoIds, String userId);
}
