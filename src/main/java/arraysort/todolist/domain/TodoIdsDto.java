package arraysort.todolist.domain;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class TodoIdsDto {

    @NotNull(message = "체크된 항목이 없습니다. 상태반영을 진행할 수 없습니다.")
    private List<Long> checkedTodoIds;

    @NotNull(message = "일정이 존재하지 않습니다. 상태반영을 진행할 수 없습니다.")
    private List<Long> allTodoIds;

}
