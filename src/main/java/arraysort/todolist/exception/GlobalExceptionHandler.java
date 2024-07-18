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

    // 회원가입 시 아이디 중복
    @ExceptionHandler(DuplicateUserException.class)
    public ModelAndView handleDuplicateUserException() {
        return getModelAndView("아이디가 이미 존재합니다.");
    }

    // 세부일정 보기 중 세부일정이 없는 경우
    @ExceptionHandler(DetailNotFoundException.class)
    public ModelAndView handleDetailNotFoundException() {
        return getModelAndView("세부 일정을 찾을 수 없습니다.");
    }

    // 삭제 중 해당 일정이 없는 경우
    @ExceptionHandler(IdNotFoundException.class)
    public ModelAndView handleIdNotFoundException() {
        return getModelAndView("해당 일정을 찾을 수 없습니다.");
    }

    @ExceptionHandler(DoneCheckNotFoundException.class)
    public ModelAndView handleDoneCheckNotFoundException() {
        return getModelAndView("선택된 일정이 없습니다.");
    }

    // @Valid 유효성 검증에 대한 예외
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

//    @ExceptionHandler(Exception.class)
//    public void exceptionHandler(Exception e) {
//        // log.info("exception={}", e.getMessage());
//    }
}
