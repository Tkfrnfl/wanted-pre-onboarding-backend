package com.example.wanted.entitiy;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "posting")
@NoArgsConstructor
public class Posting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId; // 고유 번호

    @Column(/*nullable = false,*/ unique = true,name = "user_id")
    private Long userId;

    @Column(unique = true,name = "detail")
    private String detail;
}
