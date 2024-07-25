package arraysort.todolist.service;

import arraysort.todolist.domain.SignupDto;
import arraysort.todolist.domain.UserVO;
import arraysort.todolist.exception.DuplicateUserException;
import arraysort.todolist.mapper.SignUpMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignUpService {

    private final SignUpMapper signUpMapper;

    private final ImageService imageService;

    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @Transactional
    public void addUser(SignupDto signupDto) {
        validAddUser(signupDto);

        UserVO userVO = UserVO.of(signupDto);

        signUpMapper.insertUser(userVO);
        imageService.addImage(signupDto);
    }

    /**
     * 회원가입 시 중복회원 여부 확인
     * 입력한 패스워드 인코딩 : PasswordEncoder
     *
     * @param signupDto 회원가입 폼에서 입력한 값 : userId, userPassword, userName, ImageFile
     */
    private void validAddUser(SignupDto signupDto) {
        if (signUpMapper.selectUserCountById(signupDto.getUserId()) != 0) {
            throw new DuplicateUserException();
        }

        signupDto.encodePassword(passwordEncoder.encode(signupDto.getUserPassword()));
    }
}
