package arraysort.todolist.utils;

import arraysort.todolist.domain.ImageDto;
import arraysort.todolist.exception.ImageUploadException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Slf4j
@Component
public class ImageUtil {

    @Value("${file.upload-path}")
    private String uploadPath;

    public ImageDto uploadImage(String userId, MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            return null;
        }

        String savedName = generateSavedFileName(multipartFile.getOriginalFilename());
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
        String uploadDirPath = getUploadPath(today) + File.separator + savedName;
        File uploadImage = new File(uploadDirPath);

        try {
            multipartFile.transferTo(uploadImage);
        } catch (IOException e) {
            log.error("파일 업로드 에러 : {}", multipartFile.getOriginalFilename(), e);
            throw new ImageUploadException();
        }

        return ImageDto.builder()
                .userId(userId)
                .originalName(multipartFile.getOriginalFilename())
                .savedName(savedName)
                .imageSize(multipartFile.getSize())
                .build();
    }

    private String generateSavedFileName(final String imageName) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String extension = StringUtils.getFilenameExtension(imageName);
        return uuid + "." + extension;
    }

    private String getUploadPath(final String addPath) {
        return makeDirectories(uploadPath + File.separator + addPath);
    }

    private String makeDirectories(final String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir.getPath();
    }
}
