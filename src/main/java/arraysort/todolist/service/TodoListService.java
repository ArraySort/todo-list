package arraysort.todolist.service;

import arraysort.todolist.domain.TodoEditDto;
import arraysort.todolist.domain.TodoListDto;
import arraysort.todolist.domain.TodoVO;
import arraysort.todolist.mapper.TodoListMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = ((UserDetails) principal).getUsername();
        todoListDto.updateUserId(userId);
        todoListDto.updateTodoDone(false);
        TodoVO todoVO = TodoVO.create(todoListDto);

        todoListMapper.createTodo(todoVO);
    }

    @Transactional(readOnly = true)
    public List<TodoListDto> getListByUserIdService() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = ((UserDetails) principal).getUsername();
        List<TodoVO> todoList = todoListMapper.getListByUserId(userId);

        return todoList.stream()
                .map(TodoListDto::list)
                .toList();
    }

    @Transactional(readOnly = true)
    public TodoEditDto getTodoDetailByTodoIdService(int todoId) {
        return TodoEditDto.edit(todoListMapper.getDetailByTodoId(todoId));
    }
}
