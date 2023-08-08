package com.example.wanted.dto;


import lombok.Setter;

@Setter
public class SignUpForm {
    private String email;

    private String password;

    public SignUpForm(String email, String password){
        this.email=email;
        this.password=password;
    }

    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }
}
