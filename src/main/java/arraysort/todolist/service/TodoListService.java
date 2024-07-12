package arraysort.todolist.service;

import arraysort.todolist.domain.TodoDto;
import arraysort.todolist.domain.TodoVO;
import arraysort.todolist.mapper.TodoListMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TodoListService {

    private final TodoListMapper todoListMapper;

    @Transactional
    public void createTodoService(TodoDto todoDto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = ((UserDetails) principal).getUsername();
        todoDto.updateUserId(userId);
        todoDto.updateTodoDone(false);
        TodoVO todoVO = TodoVO.of(todoDto);

        todoListMapper.createTodo(todoVO);
    }

    @Transactional(readOnly = true)
    public List<TodoDto> getListByUserIdService() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = ((UserDetails) principal).getUsername();
        List<TodoVO> todoList = todoListMapper.getListByUserId(userId);


        return todoList.stream()
                .map(TodoDto::of)
                .toList();
    }
}
