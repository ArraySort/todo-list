package arraysort.todolist.service;

import arraysort.todolist.domain.SignupDto;
import arraysort.todolist.domain.UserVO;
import arraysort.todolist.mapper.SignupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignupService {

    private final SignupMapper signupMapper;

    public void createUser(SignupDto signupDto) {
        UserVO userVO = UserVO.of(signupDto);

        signupMapper.insertUser(userVO);
    }
}
