package arraysort.todolist.service;

import arraysort.todolist.domain.SignupDto;
import arraysort.todolist.domain.UserVO;
import arraysort.todolist.exception.DuplicateUserException;
import arraysort.todolist.mapper.SignupMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SignupService {

    private final SignupMapper signupMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void createUserService(SignupDto signupDto) {

        if (signupMapper.checkUser(signupDto.getUserId()) != 0) {
            throw new DuplicateUserException();
        }

        signupDto.encodePassword(passwordEncoder.encode(signupDto.getUserPassword()));
        UserVO userVO = UserVO.of(signupDto);
        signupMapper.createUser(userVO);
    }
}
