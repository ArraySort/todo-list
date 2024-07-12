package arraysort.todolist.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TodoEditDto {

    private int todoId;

    private String todoTitle;

    private String todoContent;

    private String todoStart;

    private String todoEnd;

    private int todoPriority;

    public static TodoEditDto edit(TodoVO todoVO) {
        return builder()
                .todoId(todoVO.getTodoId())
                .todoTitle(todoVO.getTodoTitle())
                .todoContent(todoVO.getTodoContent())
                .todoStart(todoVO.getTodoStart())
                .todoEnd(todoVO.getTodoEnd())
                .todoPriority(todoVO.getTodoPriority())
                .build();
    }
}
