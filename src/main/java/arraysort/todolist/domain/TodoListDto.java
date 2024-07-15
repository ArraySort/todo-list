package arraysort.todolist.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TodoListDto {

    private int todoId;

    private String userId;

    private String todoTitle;

    private String todoContent;

    private String todoStart;

    private String todoEnd;

    private int todoPriority;

    private Boolean todoDone;

    public static TodoListDto of(TodoVO todoVO) {
        return TodoListDto.builder()
                .todoId(todoVO.getTodoId())
                .userId(todoVO.getUserId())
                .todoTitle(todoVO.getTodoTitle())
                .todoContent(todoVO.getTodoContent())
                .todoStart(todoVO.getTodoStart())
                .todoEnd(todoVO.getTodoEnd())
                .todoPriority(todoVO.getTodoPriority())
                .todoDone(todoVO.getTodoDone())
                .build();
    }
}
