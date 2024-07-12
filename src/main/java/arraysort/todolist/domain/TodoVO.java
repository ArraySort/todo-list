package arraysort.todolist.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TodoVO {

    private int todoId;

    private String userId;

    private String todoTitle;

    private String todoContent;

    private String todoStart;

    private String todoEnd;

    private int todoPriority;

    private Boolean todoDone;

    public static TodoVO create(TodoListDto todoListDto) {
        return TodoVO.builder()
                .userId(todoListDto.getUserId())
                .todoTitle(todoListDto.getTodoTitle())
                .todoContent(todoListDto.getTodoContent())
                .todoStart(todoListDto.getTodoStart())
                .todoEnd(todoListDto.getTodoEnd())
                .todoPriority(todoListDto.getTodoPriority())
                .todoDone(todoListDto.getTodoDone())
                .build();
    }
}
