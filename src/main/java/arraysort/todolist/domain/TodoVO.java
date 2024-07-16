package arraysort.todolist.domain;

import arraysort.todolist.utils.UserUtil;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TodoVO {

    private Long todoId;

    private String userId;

    private String todoTitle;

    private String todoContent;

    private String todoStart;

    private String todoEnd;

    private int todoPriority;

    private boolean todoDone;

    public static TodoVO of(TodoAddDto todoAddDto) {
        return TodoVO.builder()
                .userId(UserUtil.getCurrentLoginUserId())
                .todoTitle(todoAddDto.getTodoTitle())
                .todoContent(todoAddDto.getTodoContent())
                .todoStart(todoAddDto.getTodoStart())
                .todoEnd(todoAddDto.getTodoEnd())
                .todoPriority(todoAddDto.getTodoPriority())
                .todoDone(false)
                .build();
    }

    public void update(TodoUpdateDto todoUpdateDto) {
        this.todoTitle = todoUpdateDto.getTodoTitle();
        this.todoContent = todoUpdateDto.getTodoContent();
        this.todoStart = todoUpdateDto.getTodoStart();
        this.todoEnd = todoUpdateDto.getTodoEnd();
        this.todoPriority = todoUpdateDto.getTodoPriority();
    }
}
