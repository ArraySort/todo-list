package arraysort.todolist.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TodoDto {

    private String userId;

    private String todoTitle;

    private String todoContent;

    private String todoStart;

    private String todoEnd;

    private int todoPriority;

    private Boolean todoDone;

    public static TodoDto of(TodoVO todoVO) {
        return TodoDto.builder()
                .todoTitle(todoVO.getTodoTitle())
                .todoContent(todoVO.getTodoContent())
                .todoStart(todoVO.getTodoStart())
                .todoEnd(todoVO.getTodoEnd())
                .todoPriority(todoVO.getTodoPriority())
                .todoDone(todoVO.getTodoDone())
                .build();
    }

    public void updateUserId(String userId) {
        this.userId = userId;
    }

    public void updateTodoDone(boolean todoDone) {
        this.todoDone = todoDone;
    }
}
