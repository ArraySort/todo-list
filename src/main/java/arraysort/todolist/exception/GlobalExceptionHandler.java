package arraysort.todolist.exception;

import arraysort.todolist.domain.SignupDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ModelAndView MethodArgumentNotValidException(MethodArgumentNotValidException exception) {

        ModelAndView mav = new ModelAndView();
        BindingResult bindingResult = exception.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : fieldErrors) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        mav.addObject("user", bindingResult.getTarget());
        mav.addObject("validationErrors", errors);

        return mav;
    }

    // 아이디 중복 예외 처리
    @ExceptionHandler(DuplicateKeyException.class)
    public ModelAndView handleDuplicateKeyException(DuplicateKeyException e) {
        ModelAndView mav = new ModelAndView("signup");
        mav.addObject("user", new SignupDto());
        mav.addObject("duplicateError", "아이디가 이미 존재합니다.");

        return mav;
    }
}
