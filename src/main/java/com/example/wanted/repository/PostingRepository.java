package com.example.wanted.repository;

import com.example.wanted.entitiy.Posting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostingRepository extends JpaRepository<Posting,Long>
{
    Page<Posting> findAll(Pageable pageable);
}
