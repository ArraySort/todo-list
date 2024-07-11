package arraysort.todolist.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoDto {

    private String userId;

    private String todoTitle;

    private String todoContent;

    private String todoStart;

    private String todoEnd;

    private int todoPriority;

    private boolean todoDone;

    public void updateUserId(String userId) {
        this.userId = userId;
    }

    public void updateTodoDone(boolean todoDone) {
        this.todoDone = todoDone;
    }
}
