package arraysort.todolist.controller;

import arraysort.todolist.domain.TodoListDto;
import arraysort.todolist.domain.TodoUpdateDto;
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

    // 일정 입력
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

    // 일정 수정
    @GetMapping("/list/{todoId}/edit")
    public String todoEditForm(@PathVariable int todoId, Model model) {
        model.addAttribute("update", todoListService.getTodoDetailByTodoIdService(todoId));
        return "todo/todoEdit";
    }

    // 일정 삭제
    @PostMapping("/list/{todoId}")
    public String todoDelete(@PathVariable int todoId) {
        todoListService.deleteTodoService(todoId);
        return "redirect:/todo/list";
    }


    // 일정 저장
    @PostMapping("/list/{todoId}/edit")
    public String todoEdit(@PathVariable int todoId, @ModelAttribute("update") TodoUpdateDto todoUpdateDto) {
        todoListService.updateTodoService(todoId, todoUpdateDto);
        return "redirect:/todo/list/{todoId}";
    }

    // 로그아웃
    @PostMapping("/logout-process")
    public String logoutProcess() {
        return "login";
    }
}
