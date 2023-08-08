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

import java.util.ArrayList;
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

        List<User> user=userRepository.findUsersByEmail(email);

        posting.setUserId(user.get(0).getUserId());
        posting.setDetail(detail);

        postingRepository.save(posting);

        return "글쓰기 성공";
    }

    @Transactional
    public Page<Posting> getAllPostingService(int page,int size){
        Pageable pageable= PageRequest.of(page,size);
        Page<Posting> postings=postingRepository.findAll(pageable);
        List<Posting> pageToListNewItems = new ArrayList<Posting>();

        if(postings!=null && postings.hasContent()){
            pageToListNewItems=postings.getContent();
        }
        // System.out.println(pageToListNewItems.get(0).getPostId());
        return postings;
    }

    @Transactional
    public Posting getOnePostingService(Long postingId){
        System.out.println(postingRepository.findPostingByPostId(postingId));
        return postingRepository.findPostingByPostId(postingId);
    }
    @Transactional
    public Posting patchPostingService(Long postingId,String detail,String email){

        List<User> user=userRepository.findUsersByEmail(email);
        Posting posting= postingRepository.findPostingByPostIdAndUserId(postingId, user.get(0).getUserId());
        posting.setDetail(detail);

        return posting;
    }
    @Transactional
    public String deletePostingService(Long postingId,String email){
        List<User> user=userRepository.findUsersByEmail(email);
        postingRepository.deletePostingByPostIdAndUserId(postingId, user.get(0).getUserId());

        return "삭제 완료";
    }
}

