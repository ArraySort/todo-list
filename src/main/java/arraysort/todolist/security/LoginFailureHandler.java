package arraysort.todolist.security;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        if (exception instanceof BadCredentialsException) {
            request.setAttribute("loginError", "아이디 또는 비밀번호가 잘못되었습니다.");
        } else {
            request.setAttribute("loginError", "알 수 없는 오류가 발생했습니다.");
        }
        request.getSession().setAttribute("loginError", "아이디 또는 비밀번호가 잘못되었습니다.");
        response.sendRedirect("/login-process");
    }
}
