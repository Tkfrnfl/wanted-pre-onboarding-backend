package com.example.wanted.entitiy;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId; // 유저 고유 번호

    @Column(/*nullable = false,*/ unique = true)
    private String email;

    @Column(unique = true)
    private String password;


}
