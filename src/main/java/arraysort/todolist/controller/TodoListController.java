package arraysort.todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
/**
 * 페이지 확인을 위해서 GET, POST 매핑 상관없이 GET 으로 통일
 * TODO : 목적에 따라서 Mapping 변경, PathVariable 사용해서 페이지 경로 조정 -> 동적 생성
 */
public class TodoListController {

    // 로그인 버튼
    @GetMapping("/todoList")
    public String todolist() {
        return "todoList";
    }

    // 일정 등록 버튼
    @GetMapping("/todoAdd")
    public String todoAdd() {
        return "todoAdd";
    }

    // 일정 수정 버튼
    @GetMapping("/todoEdit")
    public String todoEdit() {
        return "todoEdit";
    }

    // 일정 눌렀을 때
    @GetMapping("/todoDetail")
    public String returnTodo() {
        return "todoDetail";
    }
}
