package arraysort.todolist.service;

import arraysort.todolist.domain.*;
import arraysort.todolist.exception.CheckedNotFoundException;
import arraysort.todolist.exception.DetailNotFoundException;
import arraysort.todolist.exception.IdNotFoundException;
import arraysort.todolist.mapper.TodoListMapper;
import arraysort.todolist.utils.UserUtil;
import arraysort.todolist.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TodoListService {

    private final TodoListMapper todoListMapper;

    // 할 일 추가
    @Transactional
    public void addTodo(TodoAddDto todoAddDto) {
        TodoVO todoVO = TodoVO.of(todoAddDto);
        todoListMapper.insertTodo(todoVO);
    }

    // 할 일 목록 조회, 페이징
    @Transactional(readOnly = true)
    public PaginationDto findTodoListWithPaging(TodoListPageDto pageDto) {
        int totalCount = todoListMapper.selectTotalCount(
                UserUtil.getCurrentLoginUserId(),
                pageDto.isDone(),
                pageDto.getSearchTitle()
        );

        TodoListQueryDto queryDto = new TodoListQueryDto(
                UserUtil.getCurrentLoginUserId(),
                pageDto.isDone(),
                pageDto.getSearchTitle(),
                10,
                (pageDto.getPage()-1) * 10
        );

        List<TodoListDto> todoListDto = todoListMapper.selectTodoListByUserId(queryDto)
                .stream()
                .map(TodoListDto::of)
                .toList();
        
        return new PaginationDto(totalCount, pageDto.getPage(), pageDto.isDone(), todoListDto);
    }

    // 세부 일정 조회
    @Transactional(readOnly = true)
    public TodoDetailDto findTodoDetailByTodoId(long todoId) {
        return TodoDetailDto.of(todoListMapper.selectTodoDetailByTodoId(todoId, UserUtil.getCurrentLoginUserId())
                .orElseThrow(DetailNotFoundException::new));
    }

    // 할 일 수정
    @Transactional
    public void modifyTodo(long todoId, TodoUpdateDto todoUpdateDto) {
        TodoVO todoVO = getModifyTodoVO(todoId, todoUpdateDto);
        todoListMapper.updateTodo(todoId, todoVO);
    }

    // 할 일 삭제
    @Transactional
    public void removeTodo(long todoId) {
        validRemoveTodo(todoId);
        todoListMapper.deleteTodo(todoId);
    }

    // 상태 최신화
    @Transactional
    public void modifyTodoDone(List<Long> checkedTodoIds, List<Long> allTodoIds) {
        validModifyTodoDone(checkedTodoIds, allTodoIds);

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

    // 체크된 할 일의 삭제
    @Transactional
    public void removeCheckedTodos(List<Long> checkedTodoIds) {
        validRemoveCheckedTodos(checkedTodoIds);
        todoListMapper.deleteCheckedTodos(checkedTodoIds);
    }

    /**
     * ModifyTodo(할 일 수정) TodoVO 생성 시 검증
     *
     * @param todoId        할 일의 고유 번호
     * @param todoUpdateDto 업데이트 폼 Dto : Title, Content, Start, End, Priority
     * @return 업데이트 폼을 통해 입력된 값으로 업데이트 된 TodoVO
     */
    private TodoVO getModifyTodoVO(long todoId, TodoUpdateDto todoUpdateDto) {
        TodoVO todoVO = todoListMapper.selectTodoDetailByTodoId(todoId, UserUtil.getCurrentLoginUserId())
                .orElseThrow(DetailNotFoundException::new);
        todoVO.update(todoUpdateDto);
        return todoVO;
    }

    /**
     * RemoveTodo(할 일 삭제)의 검증
     *
     * @param todoId 할 일의 고유 번호
     */
    private void validRemoveTodo(long todoId) {
        Optional<Integer> existTodoId = todoListMapper.selectExistTodoId(todoId, UserUtil.getCurrentLoginUserId());
        if (existTodoId.isEmpty()) {
            throw new IdNotFoundException();
        }
    }

    /**
     * ModifyTodoDone(상태 최신화)의 검증
     *
     * @param checkedTodoIds 체크박스가 체크 된 할 일의 고유번호들
     * @param allTodoIds     체크박스 유무와 상관 없는 모든 할 일의 고유번호들
     */
    private void validModifyTodoDone(List<Long> checkedTodoIds, List<Long> allTodoIds) {
        List<Long> validCheckedTodoIds = checkedTodoIds.isEmpty() ? List.of() : todoListMapper.selectExistTodoIdList(checkedTodoIds, UserUtil.getCurrentLoginUserId());
        List<Long> validAllTodoIds = allTodoIds.isEmpty() ? List.of() : todoListMapper.selectExistTodoIdList(allTodoIds, UserUtil.getCurrentLoginUserId());

        if (checkedTodoIds.size() != validCheckedTodoIds.size() || allTodoIds.size() != validAllTodoIds.size()) {
            throw new IdNotFoundException();
        }
    }

    /**
     * RemoveCheckedTodos(체크 된 할 일의 삭제)의 검증
     *
     * @param checkedTodoIds 체크박스가 체크 된 할 일의 고유번호들
     */
    private void validRemoveCheckedTodos(List<Long> checkedTodoIds) {
        if (ValidationUtil.isNullOrEmptyList(checkedTodoIds)) {
            throw new CheckedNotFoundException();
        }

        List<Long> validCheckedTodoIds = todoListMapper.selectExistTodoIdList(checkedTodoIds, UserUtil.getCurrentLoginUserId());

        if (checkedTodoIds.size() != validCheckedTodoIds.size()) {
            throw new IdNotFoundException();
        }
    }
}
