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
        imageMapper.insertImage(imageDto);
    }
}
