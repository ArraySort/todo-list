package arraysort.todolist.utils;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ValidationUtil {

    private ValidationUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 리스트에 대한 null, Empty 여부 확인
     *
     * @param list 검증이 필요한 리스트
     * @return null 이거나 Empty 인 경우 true
     * @param <T> 검증이 필요한 리스트의 타입
     */
    public static <T> boolean isNullOrEmptyList(List<T> list) {
        return list == null || list.isEmpty();
    }

    /**
     * MultipartFile 에 대한 null, Empty 여부 확인
     *
     * @param multipartFile 이미지 업로드
     * @return null 이거나 Empty 인 경우 true
     */
    public static boolean isNullOrEmptyMultipartFile(MultipartFile multipartFile) {
        return multipartFile == null || multipartFile.isEmpty();
    }
}
