package arraysort.todolist.controller;

import arraysort.todolist.domain.PaginationDto;
import arraysort.todolist.domain.TodoAddDto;
import arraysort.todolist.domain.TodoUpdateDto;
import arraysort.todolist.service.AuthService;
import arraysort.todolist.service.TodoListService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoListController {

    private final TodoListService todoListService;
    private final AuthService authService;

    // 할 일 목록
    @GetMapping("/list")
    public String todoList(Model model,
                           @RequestParam(value = "page", defaultValue = "1") int page,
                           @RequestParam(value = "done", defaultValue = "false") boolean done) {
        PaginationDto paginationDto = new PaginationDto(todoListService.findTotalPageCount(done), page);
        model.addAttribute("lists", todoListService.findTodoListByUserId(paginationDto, done));
        model.addAttribute("currentPage", page);
        model.addAttribute("pagination", paginationDto);
        model.addAttribute("userName", authService.findUserNameByUserId());
        model.addAttribute("done", done);
        return "todo/todoList";
    }

    @PostMapping("/list/updateTodoDone")
    public String todoListDoneCheck(@RequestParam(value = "todoIds", required = false) List<Long> todoIds,
                                    @RequestParam(value = "allTodoIds", required = false) List<Long> allTodoIds) {
        todoListService.modifyTodoDone(todoIds, allTodoIds);
        return "redirect:/todo/list";
    }

    @PostMapping("/list/deleteTodos")
    public String removeCheckedTodos(@RequestParam(value = "todoIds", required = false) List<Long> todoIds) {
        todoListService.removeCheckedTodos(todoIds);
        return "redirect:/todo/list";
    }

    // 일정 입력
    @GetMapping("/add")
    public String todoAddForm() {
        return "todo/todoAdd";
    }

    // 일정 등록
    @PostMapping("/add")
    public String todoAdd(@Valid @ModelAttribute TodoAddDto todoAddDto) {
        todoListService.addTodo(todoAddDto);
        return "redirect:/todo/list";
    }

    // 일정 상세조회
    @GetMapping("/list/{todoId}")
    public String todoDetails(@PathVariable int todoId, Model model) {
        model.addAttribute("details", todoListService.findTodoDetailByTodoId(todoId));
        return "todo/todoDetail";
    }

    // 일정 수정
    @GetMapping("/list/{todoId}/edit")
    public String todoModifyForm(@PathVariable int todoId, Model model) {
        model.addAttribute("update", todoListService.findTodoDetailByTodoId(todoId));
        return "todo/todoEdit";
    }

    // 일정 삭제
    @PostMapping("/list/{todoId}")
    public String todoRemove(@PathVariable int todoId) {
        todoListService.removeTodo(todoId);
        return "redirect:/todo/list";
    }

    // 일정 저장
    @PostMapping("/list/{todoId}/edit")
    public String todoModify(@PathVariable int todoId, @Valid @ModelAttribute("update") TodoUpdateDto todoUpdateDto) {
        todoListService.modifyTodo(todoId, todoUpdateDto);
        return "redirect:/todo/list/{todoId}";
    }
}
