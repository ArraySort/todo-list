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

    public static TodoVO of(TodoDto todoDto) {
        return TodoVO.builder()
                .userId(todoDto.getUserId())
                .todoTitle(todoDto.getTodoTitle())
                .todoContent(todoDto.getTodoContent())
                .todoStart(todoDto.getTodoStart())
                .todoEnd(todoDto.getTodoEnd())
                .todoPriority(todoDto.getTodoPriority())
                .todoDone(todoDto.getTodoDone())
                .build();
    }
}
