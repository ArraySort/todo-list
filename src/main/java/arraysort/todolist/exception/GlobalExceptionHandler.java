package arraysort.todolist.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(DuplicateUserException.class)
    public ModelAndView handleDuplicateUserException() {
        return getModelAndView("아이디가 이미 존재합니다.");
    }

    @ExceptionHandler(DetailNotFoundException.class)
    public ModelAndView handleDetailNotFoundException() {
        return getModelAndView("세부 일정을 찾을 수 없습니다.");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ModelAndView handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        StringBuilder errorMessages = new StringBuilder();

        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errorMessages.append(error.getDefaultMessage());
        }

        return getModelAndView(String.valueOf(errorMessages));
    }

    private ModelAndView getModelAndView(String message) {
        ModelAndView mav = new ModelAndView("error/alert");
        mav.addObject("errorMessage", message);
        return mav;
    }
    //@ExceptionHandler(Exception.class) TODO: 지정 예외 제외하고 모든 예외에 대해서(로그 필요)
}
