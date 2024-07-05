package arraysort.todolist.service;

import arraysort.todolist.domain.SignupDto;
import arraysort.todolist.domain.UserVO;
import arraysort.todolist.mapper.SignupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignupService {

    private final SignupMapper signupMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void createUser(SignupDto signupDto) {
        signupDto.encodePassword(passwordEncoder.encode(signupDto.getUserPassword()));
        UserVO userVO = UserVO.of(signupDto);
        signupMapper.insertUser(userVO);
    }

    @Transactional
    public int checkUser(SignupDto signupDto) {
        UserVO userVO = UserVO.of(signupDto);
        return signupMapper.checkUser(userVO);
    }
}
