package arraysort.todolist.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class TodoIdsDto {

    private List<Long> checkedTodoIds;

    private List<Long> allTodoIds;

}
