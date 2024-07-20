package arraysort.todolist.service;


import arraysort.todolist.domain.ImageDto;
import arraysort.todolist.mapper.ImageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageMapper imageMapper;

    @Transactional
    public void addImage(ImageDto imageDto) {
        if (imageDto == null) {
            return;
        }
        imageMapper.insertImage(imageDto);
    }

    @Transactional(readOnly = true)
    public String findImageByUserId(String userId) {
        String savedName = imageMapper.findSavedImageNameByUserId(userId);

        // 저장된 이미지가 없을 때 기본 이미지 경로 반환
        if (savedName == null) {
            return "/images/userProfile.png";
        }

        return savedName;
    }
}
