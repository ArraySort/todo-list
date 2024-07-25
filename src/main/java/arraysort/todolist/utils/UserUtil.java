package arraysort.todolist.utils;

import arraysort.todolist.exception.InvalidPrincipalException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class UserUtil {

    private UserUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 로그인 한 유저의 Username(userId) 를 가져옴
     *
     * @return 로그인 한 유저의 userId
     */
    public static String getCurrentLoginUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal == null) throw new InvalidPrincipalException();

        return ((UserDetails) principal).getUsername();
    }

    /**
     * 인가된 사용자인지 검사
     *
     * @param authentication Spring Security 의 인가
     * @return 인가 여부
     */
    public static boolean isAuthenticatedUser(Authentication authentication) {
        return authentication != null && authentication.isAuthenticated();
    }
}
