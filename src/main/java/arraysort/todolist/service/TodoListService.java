package arraysort.todolist.service;

import arraysort.todolist.domain.*;
import arraysort.todolist.exception.DetailNotFoundException;
import arraysort.todolist.mapper.TodoListMapper;
import arraysort.todolist.utils.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TodoListService {

    private final TodoListMapper todoListMapper;

    @Transactional
    public void addTodo(TodoAddDto todoAddDto) {
        TodoVO todoVO = TodoVO.of(todoAddDto, UserUtil.getCurrentLoginUserId());
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
    public TodoEditDto findTodoDetailByTodoId(int todoId) {
        return TodoEditDto.of(todoListMapper.selectTodoDetailByTodoId(todoId)
                .orElseThrow(DetailNotFoundException::new));
    }

    @Transactional
    public void modifyTodo(int todoId, TodoUpdateDto todoUpdateDto) {
        TodoVO todoVO = todoListMapper.selectTodoDetailByTodoId(todoId)
                .orElseThrow(DetailNotFoundException::new);
        todoVO.update(todoUpdateDto);
        todoListMapper.updateTodo(todoId, todoVO);
    }

    @Transactional
    public void removeTodo(int todoId) {
        todoListMapper.deleteTodo(todoId);
    }
}
