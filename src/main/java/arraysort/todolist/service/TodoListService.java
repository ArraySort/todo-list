package arraysort.todolist.service;

import arraysort.todolist.domain.TodoDto;
import arraysort.todolist.domain.TodoVO;
import arraysort.todolist.mapper.TodoListMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
public class TodoListService {

    private final TodoListMapper todoListMapper;

    @Transactional
    public void createTodo(TodoDto todoDto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        todoDto.updateUserId(username);
        
        TodoVO todoVO = TodoVO.of(todoDto);
        todoListMapper.createTodo(todoVO);
    }
}
