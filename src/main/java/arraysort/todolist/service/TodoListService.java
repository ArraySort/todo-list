package arraysort.todolist.service;

import arraysort.todolist.domain.*;
import arraysort.todolist.exception.CheckedNotFoundException;
import arraysort.todolist.exception.DetailNotFoundException;
import arraysort.todolist.exception.IdNotFoundException;
import arraysort.todolist.mapper.TodoListMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static arraysort.todolist.utils.UserUtil.getCurrentLoginUserId;

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
    public PaginationDto findTodoListWithPaging(int currentPage, boolean todoDone) {
        int totalCount = todoListMapper.selectTotalCount(getCurrentLoginUserId(), todoDone);
        totalCount = totalCount == 0 ? 1 : totalCount;

        int offset = (currentPage - 1) * 10;
        
        List<TodoListDto> todoListDto = todoListMapper.selectTodoListByUserId(
                        getCurrentLoginUserId(),
                        todoDone,
                        10,
                        offset)
                .stream()
                .map(TodoListDto::of)
                .toList();

        return new PaginationDto(totalCount, currentPage, todoDone, todoListDto);
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

    @Transactional
    public void modifyTodoDone(List<Long> checkedTodoIds, List<Long> allTodoIds) {
        if (allTodoIds == null || allTodoIds.isEmpty()) {
            throw new CheckedNotFoundException();
        }

        if (checkedTodoIds == null) {
            throw new CheckedNotFoundException();
        }

        List<Long> notDoneTodoIds = allTodoIds.stream()
                .filter(id -> !checkedTodoIds.contains(id))
                .toList();

        if (!checkedTodoIds.isEmpty()) {
            todoListMapper.updateTodoDone(checkedTodoIds);
        }

        if (!notDoneTodoIds.isEmpty()) {
            todoListMapper.updateTodoNotDone(notDoneTodoIds);
        }
    }

    @Transactional
    public void removeCheckedTodos(List<Long> todoIds) {
        if (todoIds == null || todoIds.isEmpty()) {
            throw new CheckedNotFoundException();
        }

        todoListMapper.deleteCheckedTodos(todoIds);
    }
}
