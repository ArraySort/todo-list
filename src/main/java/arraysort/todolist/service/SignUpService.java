package arraysort.todolist.service;

import arraysort.todolist.common.component.ImageComponent;
import arraysort.todolist.domain.ImageDto;
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

    private final ImageComponent imageComponent;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void addUser(SignupDto signupDto) {

        if (signUpMapper.selectUserCountById(signupDto.getUserId()) != 0) {
            throw new DuplicateUserException();
        }

        signupDto.encodePassword(passwordEncoder.encode(signupDto.getUserPassword()));

        UserVO userVO = UserVO.of(signupDto);
        ImageDto image = imageComponent.uploadImage(signupDto.getUserId(), signupDto.getImageFile());

        signUpMapper.insertUser(userVO);
        imageService.addImage(image);
    }
}
