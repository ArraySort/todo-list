package arraysort.todolist.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TodoDetailDto {

    private long todoId;

    private String todoTitle;

    private String todoContent;

    private String todoStart;

    private String todoEnd;

    private int todoPriority;

    public static TodoDetailDto of(TodoVO todoVO) {
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
