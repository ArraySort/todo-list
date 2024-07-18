package arraysort.todolist.service;

import arraysort.todolist.domain.SignupDto;
import arraysort.todolist.domain.UserVO;
import arraysort.todolist.exception.DuplicateUserException;
import arraysort.todolist.mapper.SignUpMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SignUpService {

    private final SignUpMapper signUpMapper;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void addUser(SignupDto signupDto) {

        if (signUpMapper.selectUserCountById(signupDto.getUserId()) != 0) {
            throw new DuplicateUserException();
        }

        signupDto.encodePassword(passwordEncoder.encode(signupDto.getUserPassword()));
        UserVO userVO = UserVO.of(signupDto);
        signUpMapper.insertUser(userVO);
    }
}
