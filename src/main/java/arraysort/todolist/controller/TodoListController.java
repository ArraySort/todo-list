package arraysort.todolist.controller;

import arraysort.todolist.domain.TodoAddDto;
import arraysort.todolist.domain.TodoUpdateDto;
import arraysort.todolist.service.TodoListService;
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

    // 할 일 목록
    @GetMapping("/list")
    public String todoList(Model model) {
        model.addAttribute("lists", todoListService.findTodoListByUserId());
        return "todo/todoList";
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
