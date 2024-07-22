package arraysort.todolist.service;


import arraysort.todolist.domain.ImageDto;
import arraysort.todolist.mapper.ImageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageMapper imageMapper;

    @Value("${file.default-image}")
    private String defaultImage;

    @Transactional
    public void addImage(ImageDto imageDto) {
        imageMapper.insertImage(imageDto);
    }

    @Transactional(readOnly = true)
    public String findImageByUserId(String userId) {
        String savedName = imageMapper.findSavedImageNameByUserId(userId);

        return (savedName == null || savedName.isBlank()) ? defaultImage : savedName;
    }
}
