package arraysort.todolist.controller;

import arraysort.todolist.domain.TodoListDto;
import arraysort.todolist.service.TodoListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoListController {

    private final TodoListService todoListService;

    // 할 일 목록
    @GetMapping("/list")
    public String todolist(Model model) {
        model.addAttribute("lists", todoListService.getListByUserIdService());
        return "todo/todoList";
    }

    // 일정 등록 폼
    @GetMapping("/add")
    public String todoAddForm() {
        return "todo/todoAdd";
    }

    // 일정 등록
    @PostMapping("/add")
    public String todoAdd(@ModelAttribute TodoListDto todoListDto) {
        todoListService.createTodoService(todoListDto);
        return "redirect:/todo/list";
    }

    // 일정 상세조회
    @GetMapping("/list/{todoId}")
    public String todoDetail(@PathVariable int todoId, Model model) {
        model.addAttribute("details", todoListService.getTodoDetailByTodoIdService(todoId));
        return "todo/todoDetail";
    }

    // 일정 수정 버튼
    @GetMapping("/edit")
    public String todoEdit() {
        return "todo/todoEdit";
    }

    // 로그아웃
    @PostMapping("/logout-process")
    public String logoutProcess() {
        return "login";
    }
}
