package arraysort.todolist.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoListPageDto {

    private int page;

    private boolean done;

    public TodoListPageDto() {
        this.page = 1;
        this.done = false;
    }
}
