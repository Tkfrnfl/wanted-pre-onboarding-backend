package com.example.wanted.service;

import com.example.wanted.dto.SignUpForm;
import com.example.wanted.entitiy.Posting;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

@SpringBootTest
public class PostingServiceTest {

    @Autowired
    private PostingService postingService;

    @DisplayName("새로운 게시글 생성")
    @Test
    void newPostingTest() throws Exception {
        //given

        //when
        String when=postingService.newPostingService("123@","test");
        //then
        Assertions.assertThat(when).isEqualTo("글쓰기 성공");
    }
    @DisplayName("게시글 목록 조회")
    @Test
    void getAllPostingTest() throws Exception {
        //given

        //when
        Page<Posting> when=postingService.getAllPostingService(1,10);
        System.out.println(when.get());
        //then
        Assertions.assertThat(when.getTotalPages()).isEqualTo(1);
    }

}
