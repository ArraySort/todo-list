package arraysort.todolist.mapper;

import arraysort.todolist.domain.ImageDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImageMapper {

    void insertImage(ImageDto imageDto);

    String findSavedImageNameByUserId(String userId);
}
