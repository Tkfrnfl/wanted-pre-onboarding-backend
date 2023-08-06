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

    @DisplayName("게시글 특정 id로 조회")
    @Test
    void getOnePostingTest() throws Exception {
        //given

        //when
        Posting when=postingService.getOnePostingService(Long.parseLong("1"));
        //then
        Assertions.assertThat(when.getUserId()).isEqualTo(1);
    }

    @DisplayName("게시글 특정 id로 수정")
    @Test
    void  patchPostingTest() throws Exception {
        //given

        //when
        Posting when=postingService.patchPostingService(Long.parseLong("1"),"edit","124@");
        //then
        Assertions.assertThat(when.getDetail()).isEqualTo("edit");
    }

    @DisplayName("게시글 특정 id로 삭제")
    @Test
    void  deletePostingTest() throws Exception {
        //given

        //when
        String when=postingService.deletePostingService(Long.parseLong("1"),"124@");
        //then
        Assertions.assertThat(when).isEqualTo("삭제 완료");
    }
}
