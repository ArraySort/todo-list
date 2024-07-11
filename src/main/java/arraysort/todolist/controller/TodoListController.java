package arraysort.todolist.controller;

import arraysort.todolist.domain.TodoDto;
import arraysort.todolist.service.TodoListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/todo")
public class TodoListController {

    private final TodoListService todoListService;

    // 로그인 버튼
    @GetMapping("/list")
    public String todolist() {
        return "todo/todoList";
    }

    // 일정 등록 버튼
    @GetMapping("/add")
    public String todoAddForm() {
        return "todo/todoAdd";
    }

    @PostMapping("/add")
    public String todoAdd(@ModelAttribute TodoDto todoDto) {
        log.info("todoDto={}", todoDto.getTodoTitle());
        todoListService.createTodo(todoDto);
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

    @PostMapping("/logout-process")
    public String logoutProcess() {
        return "login";
    }
}
