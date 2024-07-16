package arraysort.todolist.domain;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TodoAddDto {

    @NotBlank(message = "제목은 필수 입력 항목입니다.")
    @Size(min = 2, max = 20, message = "제목은 최소 2글자 이상, 최대 20글자 이하여야 합니다.")
    private String todoTitle;

    @Size(max = 200, message = "내용은 최대 200자까지 입력할 수 있습니다.")
    private String todoContent;

    @NotBlank(message = "시작 시간은 필수 입력 항목입니다.")
    private String todoStart;

    @NotBlank(message = "종료 시간은 필수 입력 항목입니다.")
    private String todoEnd;

    @NotNull
    @Min(1)
    @Max(5)
    private Integer todoPriority;

}
