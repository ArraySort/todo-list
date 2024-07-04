package arraysort.todolist.service;

import arraysort.todolist.domain.SignupDto;
import arraysort.todolist.domain.UserVO;
import arraysort.todolist.mapper.SignupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignupService {

    private final SignupMapper signupMapper;

    @Transactional
    public void createUser(SignupDto signupDto) {
        UserVO userVO = UserVO.of(signupDto);
        signupMapper.insertUser(userVO);
    }

    @Transactional
    public int checkUser(SignupDto signupDto) {
        UserVO userVO = UserVO.of(signupDto);
        return signupMapper.checkUser(userVO);
    }
}
