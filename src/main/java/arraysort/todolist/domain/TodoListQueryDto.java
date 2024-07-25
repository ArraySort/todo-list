package arraysort.todolist.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TodoListQueryDto {

    private String userId;

    private boolean todoDone;

    private String searchTitle;

    private int limit;

    private int offset;

}
