package arraysort.todolist.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ImageDto {
    
    private String userId;

    private String originalName;

    private String savedName;

    private long imageSize;

}
