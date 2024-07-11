package arraysort.todolist.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateUserException.class)
    public ModelAndView handleDuplicateKeyException(DuplicateUserException e) {
        ModelAndView mav = new ModelAndView("signup");
        mav.addObject("duplicateUserError", "아이디가 이미 존재합니다.");
        return mav;
    }
}
