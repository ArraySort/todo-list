package arraysort.todolist.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TodoDto {

    private String userId;

    private String todoTitle;

    private String todoContent;

    private String todoStart;

    private String todoEnd;

    private int todoPriority;

    private boolean todoDone;

    public static TodoDto of(TodoVO todoVO) {
        return TodoDto.builder()
                .todoTitle(todoVO.getTodoTitle())
                .todoContent(todoVO.getTodoContent())
                .todoStart(todoVO.getTodoEnd())
                .todoEnd(todoVO.getTodoEnd())
                .todoPriority(todoVO.getTodoPriority())
                .todoDone(todoVO.isTodoDone())
                .build();
    }

    public void updateUserId(String userId) {
        this.userId = userId;
    }
}
