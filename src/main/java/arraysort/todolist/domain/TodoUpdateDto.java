package arraysort.todolist.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TodoUpdateDto {

    private String todoTitle;

    private String todoContent;

    private String todoStart;

    private String todoEnd;

    private int todoPriority;

}
