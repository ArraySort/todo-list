package arraysort.todolist.service;

import arraysort.todolist.domain.UserVO;
import arraysort.todolist.mapper.LoginMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final LoginMapper loginMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return loginMapper.getUserById(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(username));

    }

    private UserDetails createUserDetails(UserVO user) {
        return new User(
                user.getUserId(),
                user.getUserPassword(),
                Collections.singleton(new SimpleGrantedAuthority("USER"))
        );
    }

}
