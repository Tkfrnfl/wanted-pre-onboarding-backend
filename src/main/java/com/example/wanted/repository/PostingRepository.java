package com.example.wanted.repository;

import com.example.wanted.entitiy.Posting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostingRepository extends JpaRepository<Posting,Long>
{
    Posting findPostingByPostId(Long postId);
    Posting findPostingByPostIdAndUserId(Long postId,Long userId);

    void deletePostingByPostIdAndUserId(Long postId,Long userId);
}
