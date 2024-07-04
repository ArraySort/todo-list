package arraysort.todolist.validator;

import arraysort.todolist.domain.SignupDto;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return SignupDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SignupDto user = (SignupDto) target;

        if (!StringUtils.hasText(user.getUserId())) {
            errors.rejectValue("userId", "required");
        }

        if (!StringUtils.hasText(user.getUserPassword())) {
            errors.rejectValue("userPassword", "required");
        }

        if (!StringUtils.hasText(user.getUserName())) {
            errors.rejectValue("userName", "required");
        }
    }
}
