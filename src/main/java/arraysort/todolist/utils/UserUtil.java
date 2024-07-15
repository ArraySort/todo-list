package arraysort.todolist.utils;

import arraysort.todolist.exception.InvalidPrincipalException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class UserUtil {

    private UserUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static String getCurrentLoginUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        if (principal == null) {
            throw new InvalidPrincipalException();
        }

        return ((UserDetails) principal).getUsername();
    }
}
