package arraysort.todolist.common.component;

import arraysort.todolist.domain.ImageDto;
import arraysort.todolist.exception.ImageUploadException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class ImageComponent {

    @Value("${file.upload-path}")
    private String uploadPath; // 업로드 경로

    @Value("${file.allowed-extensions}")
    private String allowedExtensions; // 허용된 확장자

    /**
     * 이미지 업로드
     * 업로드 이미지 이름, 경로 지정
     *
     * @param userId        회원가입 시 기입한 사용자 아이디
     * @param multipartFile 이미지 파일
     * @return ImageDto : userId, originalName, savedName(저장된 경로), imageSize
     */
    public ImageDto uploadImage(String userId, MultipartFile multipartFile) {
        if (multipartFile == null || multipartFile.isEmpty()) {
            return null; // 이미지 파일이 없을 경우 null 전송
        }

        // 저장된 이미지 이름 지정, 저장소 디렉터리 생성을 위한 현재날짜, 생성된 디렉터리 주소 초기화
        String savedName = generateSavedFileName(multipartFile.getOriginalFilename());
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
        Path uploadDirPath = makeDirectories(Paths.get(uploadPath, today));
        Path uploadImage = uploadDirPath.resolve(savedName);

        try {
            multipartFile.transferTo(uploadImage);
        } catch (IOException e) {
            log.error("이미지 업로드 에러 : {}", multipartFile.getOriginalFilename(), e);
            throw new ImageUploadException("이미지 업로드 에러 발생");
        }

        // 이미지 파일이 있는 경우 /upload-images/현재날짜/저장된이름 경로를 포함하여 Dto 반환
        return ImageDto.builder()
                .userId(userId)
                .originalName(multipartFile.getOriginalFilename())
                .savedName("/upload-images/" + today + "/" + savedName) // 업로드 된 이미지가 DB 에 저장된 이름
                .imageSize(multipartFile.getSize())
                .build();
    }

    /**
     * 저장된 이미지 이름 생성 (UUID 로 변경)
     *
     * @param imageName 업로드 한 이미지
     * @return UUID 로 변경된 이미지 이름
     */
    private String generateSavedFileName(final String imageName) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String extension = StringUtils.getFilenameExtension(imageName);
        List<String> allowedEx = List.of(allowedExtensions.split(","));

        if (extension == null || !allowedEx.contains(extension.toLowerCase())) {
            throw new ImageUploadException("지원하지 않는 확장자입니다. 지원 형식 : " + allowedEx);
        }

        return uuid + "." + extension;
    }

    /**
     * 새로운 디렉터리 생성
     *
     * @param path 업로드 경로
     * @return 업로드 경로
     */
    private Path makeDirectories(final Path path) {
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
        } catch (IOException e) {
            log.error("디렉터리 생성 실패 : {}", path, e);
            throw new ImageUploadException("디렉터리 생성 실패");
        }
        
        return path;
    }
}
