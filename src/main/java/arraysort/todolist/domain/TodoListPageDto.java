package arraysort.todolist.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoListPageDto {

    private int page = 1;

    private boolean done = false;

}
