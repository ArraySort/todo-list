package arraysort.todolist.controller;

import arraysort.todolist.domain.*;
import arraysort.todolist.service.AuthService;
import arraysort.todolist.service.ImageService;
import arraysort.todolist.service.TodoListService;
import arraysort.todolist.utils.UserUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoListController {

    private final TodoListService todoListService;

    private final AuthService authService;

    private final ImageService imageService;

    // 할 일 목록 페이지
    @GetMapping("/list")
    public String todoList(@ModelAttribute TodoListPageDto todoListPageDto, Model model) {
        PaginationDto paginationDto = todoListService.findTodoListWithPaging(todoListPageDto);

        model.addAttribute("pagination", paginationDto);
        model.addAttribute("userImage", imageService.findImageByUserId(UserUtil.getCurrentLoginUserId()));
        model.addAttribute("userName", authService.findUserNameByUserId());
        return "todo/todoList";
    }

    // 일정 완료 및 미완료 처리 요청
    @PostMapping("/list/updateTodoDone")
    public String todoListDoneCheck(@Valid @ModelAttribute TodoIdsDto todoIdsDto, Model model) {
        todoListService.modifyTodoDone(todoIdsDto.getCheckedTodoIds(), todoIdsDto.getAllTodoIds());

        addMessageAndUrl(model, "상태 반영이 완료되었습니다.", "UPDATE_TODO_DONE");
        model.addAttribute("done", todoIdsDto.getTodoDone());
        return "common/alert";
    }

    // 선택된 일정 삭제 요청
    @PostMapping("/list/deleteTodos")
    public String removeCheckedTodos(@Valid @ModelAttribute TodoIdsDto todoIdsDto, Model model) {
        todoListService.removeCheckedTodos(todoIdsDto.getCheckedTodoIds());

        addMessageAndUrl(model, "삭제가 완료되었습니다.", "REMOVE_TODO_DONE");
        model.addAttribute("done", todoIdsDto.getTodoDone());
        return "common/alert";
    }

    // 일정 등록 페이지
    @GetMapping("/add")
    public String todoAddForm() {
        return "todo/todoAdd";
    }

    // 일정 등록 요청
    @PostMapping("/add")
    public String todoAdd(@Valid @ModelAttribute TodoAddDto todoAddDto, Model model) {
        todoListService.addTodo(todoAddDto);

        addMessageAndUrl(model, "일정이 추가되었습니다.", "ADD_TODO");
        return "common/alert";
    }

    // 일정 상세조회 페이지
    @GetMapping("/list/{todoId}")
    public String todoDetails(@PathVariable int todoId, Model model) {
        model.addAttribute("details", todoListService.findTodoDetailByTodoId(todoId));
        return "todo/todoDetail";
    }

    // 일정 수정 페이지
    @GetMapping("/list/{todoId}/edit")
    public String todoModifyForm(@PathVariable int todoId, Model model) {
        model.addAttribute("update", todoListService.findTodoDetailByTodoId(todoId));
        return "todo/todoEdit";
    }

    // 일정 삭제 요청
    @PostMapping("/list/{todoId}")
    public String todoRemove(@PathVariable int todoId, Model model) {
        todoListService.removeTodo(todoId);

        addMessageAndUrl(model, "일정이 삭제되었습니다.", "REMOVE_TODO");
        return "common/alert";
    }

    // 일정 저장 요청
    @PostMapping("/list/{todoId}/edit")
    public String todoModify(@PathVariable int todoId, @Valid @ModelAttribute("update") TodoUpdateDto todoUpdateDto, Model model) {
        todoListService.modifyTodo(todoId, todoUpdateDto);

        addMessageAndUrl(model, "일정이 업데이트 되었습니다.", "MODIFY_TODO");
        model.addAttribute("id", todoId);
        return "common/alert";
    }

    private void addMessageAndUrl(Model model, String message, String url) {
        model.addAttribute("message", message);
        model.addAttribute("url", url);
    }
}
