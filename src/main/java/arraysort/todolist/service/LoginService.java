package arraysort.todolist.service;

import arraysort.todolist.domain.UserVO;
import arraysort.todolist.mapper.LoginMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

    private final LoginMapper loginMapper;

    /**
     * Spring Security 로그인
     * @param username 유저아이디
     * @return 유저 아이디로 조회한 결과
     * @throws UsernameNotFoundException 아이디로 조회했을 때 없는 경우
     */
    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return loginMapper.selectUserById(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    /**
     * Spring Security 로그인
     * UserDetails 를 만들어주는 메서드
     * @param user UserVO
     * @return User 객체 -> loadUserByUsername 함수에 들어감
     */
    private UserDetails createUserDetails(UserVO user) {
        return new User(
                user.getUserId(),
                user.getUserPassword(),
                Collections.singleton(new SimpleGrantedAuthority("USER"))
        );
    }
}
