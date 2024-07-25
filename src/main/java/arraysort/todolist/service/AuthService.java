package arraysort.todolist.service;

import arraysort.todolist.mapper.AuthMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static arraysort.todolist.utils.UserUtil.getCurrentLoginUserId;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final AuthMapper authMapper;

    // 로그인한 유저의 userName(닉네임) 조회
    @Transactional(readOnly = true)
    public String findUserNameByUserId() {
        return authMapper.selectUserNameByUserId(getCurrentLoginUserId());
    }
}
