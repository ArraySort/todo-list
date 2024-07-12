package arraysort.todolist.controller;

import arraysort.todolist.domain.TodoDto;
import arraysort.todolist.service.TodoListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String todoAdd(@ModelAttribute TodoDto todoDto) {
        todoListService.createTodoService(todoDto);
        return "todo/todoList";
    }

    // 일정 수정 버튼
    @GetMapping("/edit")
    public String todoEdit() {
        return "todo/todoEdit";
    }

    // 일정 눌렀을 때
    @GetMapping("/detail")
    public String returnTodo() {
        return "todo/todoDetail";
    }

    // 로그아웃
    @PostMapping("/logout-process")
    public String logoutProcess() {
        return "login";
    }
}
