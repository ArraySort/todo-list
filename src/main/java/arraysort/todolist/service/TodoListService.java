package arraysort.todolist.service;

import arraysort.todolist.domain.*;
import arraysort.todolist.exception.DetailNotFoundException;
import arraysort.todolist.exception.IdNotFoundException;
import arraysort.todolist.mapper.TodoListMapper;
import arraysort.todolist.utils.UserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class TodoListService {

    private final TodoListMapper todoListMapper;

    @Transactional
    public void addTodo(TodoAddDto todoAddDto) {
        TodoVO todoVO = TodoVO.of(todoAddDto);
        todoListMapper.insertTodo(todoVO);
    }

    @Transactional(readOnly = true)
    public List<TodoListDto> findTodoListByUserId() {
        return todoListMapper.selectTodoListByUserId(UserUtil.getCurrentLoginUserId())
                .stream()
                .map(TodoListDto::of)
                .toList();
    }

    @Transactional(readOnly = true)
    public TodoDetailDto findTodoDetailByTodoId(long todoId) {
        return TodoDetailDto.of(todoListMapper.selectTodoDetailByTodoId(todoId, UserUtil.getCurrentLoginUserId())
                .orElseThrow(DetailNotFoundException::new));
    }

    @Transactional
    public void modifyTodo(long todoId, TodoUpdateDto todoUpdateDto) {
        TodoVO todoVO = todoListMapper.selectTodoDetailByTodoId(todoId, UserUtil.getCurrentLoginUserId())
                .orElseThrow(DetailNotFoundException::new);
        todoVO.update(todoUpdateDto);
        todoListMapper.updateTodo(todoId, todoVO);
    }

    @Transactional
    public void removeTodo(long todoId) {
        Optional<Integer> existTodoId = todoListMapper.selectExistTodoId(todoId, UserUtil.getCurrentLoginUserId());

        if (existTodoId.isEmpty()) {
            throw new IdNotFoundException();
        }
        
        todoListMapper.deleteTodo(todoId);
    }
}
