package com.example.wanted.service;

import com.example.wanted.config.AesKeyConfig;
import com.example.wanted.dto.SignUpForm;
import com.example.wanted.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @DisplayName("@없는 이메일")
    @Test
    void nonAtEmail() throws Exception {
        //given
            final SignUpForm signUpForm=new SignUpForm("123","");

        //when
            String when=userService.signUpService(signUpForm);
        //then
        Assertions.assertThat(when).isEqualTo("이메일은 @포함되야합니다.");
    }

    @DisplayName("@있는 이메일 ,8자리 미만 비밀번호")
    @Test
    void AtEmailWith_7Password() throws Exception {
        //given
        final SignUpForm signUpForm=new SignUpForm("123@","1234567");

        //when
        String when=userService.signUpService(signUpForm);
        //then
        Assertions.assertThat(when).isEqualTo("비밀번호는 8자리 이상입니다.");
    }

    @DisplayName("모든조건 충족하는 회원가입")
    @Test
    void AtEmailWith_8Password() throws Exception {
        //given
       final SignUpForm signUpForm=new SignUpForm("123@","12345678");
        //when
       String when=userService.signUpService(signUpForm);
        //System.out.println(aesKeyConfig.getKey());
        //then
       Assertions.assertThat(when).isEqualTo("회원가입 성공");
    }
}
