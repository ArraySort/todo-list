package arraysort.todolist.controller;

import arraysort.todolist.domain.*;
import arraysort.todolist.service.AuthService;
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
    
    private final AuthService authService;

    // 할 일 목록
    @GetMapping("/list")
    public String todoList(@ModelAttribute TodoListPageDto todoListPageDto, Model model) {
        PaginationDto paginationDto = todoListService.findTodoListWithPaging(todoListPageDto.getPage(), todoListPageDto.isDone());

        model.addAttribute("pagination", paginationDto);
        model.addAttribute("userName", authService.findUserNameByUserId());
        return "todo/todoList";
    }

    // 일정 완료 및 미완료 처리
    @PostMapping("/list/updateTodoDone")
    public String todoListDoneCheck(@ModelAttribute TodoIdsDto todoIdsDto) {
        todoListService.modifyTodoDone(todoIdsDto.getTodoIds(), todoIdsDto.getAllTodoIds());
        return "redirect:/todo/list";
    }

    // 선택된 일정 삭제
    @PostMapping("/list/deleteTodos")
    public String removeCheckedTodos(@ModelAttribute TodoIdsDto todoIdsDto) {
        todoListService.removeCheckedTodos(todoIdsDto.getTodoIds());
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
