package arraysort.todolist.service;

import arraysort.todolist.domain.*;
import arraysort.todolist.exception.DetailNotFoundException;
import arraysort.todolist.exception.DoneCheckNotFoundException;
import arraysort.todolist.exception.IdNotFoundException;
import arraysort.todolist.mapper.TodoListMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static arraysort.todolist.utils.UserUtil.getCurrentLoginUserId;

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
    public List<TodoListDto> findTodoListByUserId(PaginationDto paginationDto, boolean todoDone) {
        return todoListMapper.selectTodoListByUserId(getCurrentLoginUserId(), todoDone,
                        paginationDto.getRowCount(),
                        paginationDto.getOffset())
                .stream()
                .map(TodoListDto::of)
                .toList();
    }

    @Transactional(readOnly = true)
    public TodoDetailDto findTodoDetailByTodoId(long todoId) {
        return TodoDetailDto.of(todoListMapper.selectTodoDetailByTodoId(todoId, getCurrentLoginUserId())
                .orElseThrow(DetailNotFoundException::new));
    }

    @Transactional
    public void modifyTodo(long todoId, TodoUpdateDto todoUpdateDto) {
        TodoVO todoVO = todoListMapper.selectTodoDetailByTodoId(todoId, getCurrentLoginUserId())
                .orElseThrow(DetailNotFoundException::new);
        todoVO.update(todoUpdateDto);
        todoListMapper.updateTodo(todoId, todoVO);
    }

    @Transactional
    public void removeTodo(long todoId) {
        Optional<Integer> existTodoId = todoListMapper.selectExistTodoId(todoId, getCurrentLoginUserId());
        if (existTodoId.isEmpty()) {
            throw new IdNotFoundException();
        }

        todoListMapper.deleteTodo(todoId);
    }

    @Transactional(readOnly = true)
    public int findTotalPageCount(boolean todoDone) {
        int totalPageCount = todoListMapper.selectTotalCount(getCurrentLoginUserId(), todoDone);
        if (totalPageCount == 0) {
            return 1;
        }

        return totalPageCount;
    }

    @Transactional
    public void modifyTodoDone(List<Long> todoIds) {
        if (todoIds == null) {
            throw new DoneCheckNotFoundException();
        }
        
        todoListMapper.updateTodoDone(todoIds);
    }
}
