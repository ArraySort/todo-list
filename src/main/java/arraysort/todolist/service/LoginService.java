package arraysort.todolist.service;

import arraysort.todolist.domain.LoginDto;
import arraysort.todolist.domain.UserVO;
import arraysort.todolist.mapper.LoginMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    private final LoginMapper loginMapper;

    @Transactional
    public Optional<UserVO> userIdCheck(LoginDto loginDto) {
        UserVO userVO = UserVO.of(loginDto);
        return loginMapper.getUserById(userVO.getUserId());
    }
}
