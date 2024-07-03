package arraysort.todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TODO : 목적에 따라서 Mapping 변경, PathVariable 사용해서 페이지 경로 조정 -> 동적 생성
 */
@Controller
@RequestMapping("/todo")
public class TodoListController {

    // 로그인 버튼
    @GetMapping("/list")
    public String todolist() {
        return "todoList";
    }

    // 일정 등록 버튼
    @GetMapping("/add")
    public String todoAdd() {
        return "todoAdd";
    }

    // 일정 수정 버튼
    @GetMapping("/edit")
    public String todoEdit() {
        return "todoEdit";
    }

    // 일정 눌렀을 때
    @GetMapping("/detail")
    public String returnTodo() {
        return "todoDetail";
    }
}
