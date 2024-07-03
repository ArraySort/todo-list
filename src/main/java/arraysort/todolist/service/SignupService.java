package arraysort.todolist.service;

import arraysort.todolist.domain.SignupDto;
import arraysort.todolist.mapper.SignupMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SignupService {

    private final SignupMapper signupMapper;

    public void createUser(SignupDto signupDto) {
        signupMapper.insert(signupDto);
    }
}
