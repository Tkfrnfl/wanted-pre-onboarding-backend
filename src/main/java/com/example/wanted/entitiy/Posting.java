package com.example.wanted.entitiy;

import jakarta.persistence.*;

@Entity(name = "posting")

public class Posting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId; // 고유 번호

    @Column(/*nullable = false,*/ unique = true)
    private Long userId;

    @Column(unique = true)
    private String detail;
}
