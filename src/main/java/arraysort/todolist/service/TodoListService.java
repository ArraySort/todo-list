package arraysort.todolist.service;

import arraysort.todolist.domain.TodoEditDto;
import arraysort.todolist.domain.TodoListDto;
import arraysort.todolist.domain.TodoUpdateDto;
import arraysort.todolist.domain.TodoVO;
import arraysort.todolist.mapper.TodoListMapper;
import arraysort.todolist.utils.UserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class TodoListService {

    private final TodoListMapper todoListMapper;

    @Transactional
    public void createTodoService(TodoListDto todoListDto) {
        todoListDto.updateUserId(UserUtil.getCurrentLoginUserId());
        TodoVO todoVO = TodoVO.create(todoListDto);
        todoListMapper.createTodo(todoVO);
    }

    @Transactional(readOnly = true)
    public List<TodoListDto> getListByUserIdService() {
        List<TodoVO> todoList = todoListMapper.getListByUserId(UserUtil.getCurrentLoginUserId());

        return todoList.stream()
                .map(TodoListDto::list)
                .toList();
    }

    @Transactional(readOnly = true)
    public TodoEditDto getTodoDetailByTodoIdService(int todoId) {
        return TodoEditDto.edit(todoListMapper.getDetailByTodoId(todoId));
    }

    @Transactional
    public void updateTodoService(int todoId, TodoUpdateDto todoUpdateDto) {
        TodoVO todoVO = todoListMapper.getDetailByTodoId(todoId);
        todoVO.update(todoUpdateDto);
        todoListMapper.updateTodo(todoId, todoVO);
    }
}
