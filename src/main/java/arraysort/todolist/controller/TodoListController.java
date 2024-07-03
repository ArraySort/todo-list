package arraysort.todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/todo")
public class TodoListController {

    // 로그인 버튼
    @GetMapping("/list")
    public String todolist() {
        return "todo/todoList";
    }

    // 일정 등록 버튼
    @GetMapping("/add")
    public String todoAdd() {
        return "todo/todoAdd";
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
}
