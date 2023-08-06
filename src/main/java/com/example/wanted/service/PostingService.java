package com.example.wanted.service;

import com.example.wanted.entitiy.Posting;
import com.example.wanted.entitiy.User;
import com.example.wanted.jwt.JwtTokenProvider;
import com.example.wanted.repository.PostingRepository;
import com.example.wanted.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostingService {

    private final PostingRepository postingRepository;
    private final UserRepository userRepository;
    @Autowired
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public String newPostingService(String email,String detail){
        Posting posting =new Posting();

        User user=userRepository.findUserByEmail(email);

        posting.setUserId(user.getUserId());
        posting.setDetail(detail);

        postingRepository.save(posting);

        return "글쓰기 성공";
    }

    @Transactional
    public Page<Posting> getAllPostingService(int page,int size){
        Pageable pageable= PageRequest.of(page,size);
        Page<Posting> postings=postingRepository.findAll(pageable);
        return postings;
    }
}
