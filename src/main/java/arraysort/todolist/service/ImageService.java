package arraysort.todolist.service;


import arraysort.todolist.common.component.ImageComponent;
import arraysort.todolist.domain.ImageDto;
import arraysort.todolist.domain.SignupDto;
import arraysort.todolist.mapper.ImageMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageMapper imageMapper;

    private final ImageComponent imageComponent;

    @Value("${file.default-image}")
    private String defaultImage;

    @Transactional
    public void addImage(SignupDto signupDto) {
        ImageDto image = imageComponent.uploadImage(signupDto.getUserId(), signupDto.getImageFile());

        if (image != null) {
            imageMapper.insertImage(image);
        }
    }

    @Transactional(readOnly = true)
    public String findImageByUserId(String userId) {
        String savedName = imageMapper.findSavedImageNameByUserId(userId);

        return StringUtils.isBlank(savedName) ? defaultImage : savedName;
    }
}
