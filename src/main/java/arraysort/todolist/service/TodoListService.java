package arraysort.todolist.service;

import arraysort.todolist.domain.*;
import arraysort.todolist.exception.CheckedNotFoundException;
import arraysort.todolist.exception.DetailNotFoundException;
import arraysort.todolist.exception.IdNotFoundException;
import arraysort.todolist.mapper.TodoListMapper;
import arraysort.todolist.utils.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TodoListService {

    private final TodoListMapper todoListMapper;

    private final ImageService imageService;

    @Transactional
    public void addTodo(TodoAddDto todoAddDto) {
        TodoVO todoVO = TodoVO.of(todoAddDto);
        todoListMapper.insertTodo(todoVO);
    }

    @Transactional(readOnly = true)
    public PaginationDto findTodoListWithPaging(int currentPage, boolean todoDone) {
        int totalCount = todoListMapper.selectTotalCount(UserUtil.getCurrentLoginUserId(), todoDone);
        int offset = (currentPage - 1) * 10;

        List<TodoListDto> todoListDto = todoListMapper.selectTodoListByUserId(
                        UserUtil.getCurrentLoginUserId(),
                        todoDone,
                        10,
                        offset)
                .stream()
                .map(TodoListDto::of)
                .toList();

        String savedImage = imageService.findImageByUserId(UserUtil.getCurrentLoginUserId());

        return new PaginationDto(totalCount, currentPage, todoDone, todoListDto, savedImage);
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

    @Transactional
    public void modifyTodoDone(List<Long> checkedTodoIds, List<Long> allTodoIds) {
        List<Long> validCheckedTodoIds = checkedTodoIds.isEmpty() ? List.of() : todoListMapper.selectExistTodoIdList(checkedTodoIds, UserUtil.getCurrentLoginUserId());
        List<Long> validAllTodoIds = allTodoIds.isEmpty() ? List.of() : todoListMapper.selectExistTodoIdList(allTodoIds, UserUtil.getCurrentLoginUserId());

        if (checkedTodoIds.size() != validCheckedTodoIds.size() || allTodoIds.size() != validAllTodoIds.size()) {
            throw new IdNotFoundException();
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
    public void removeCheckedTodos(List<Long> checkedTodoIds) {
        if (checkedTodoIds == null || checkedTodoIds.isEmpty()) {
            throw new CheckedNotFoundException();
        }

        List<Long> validCheckedTodoIds = todoListMapper.selectExistTodoIdList(checkedTodoIds, UserUtil.getCurrentLoginUserId());

        if (checkedTodoIds.size() != validCheckedTodoIds.size()) {
            throw new IdNotFoundException();
        }

        todoListMapper.deleteCheckedTodos(checkedTodoIds);
    }
}
