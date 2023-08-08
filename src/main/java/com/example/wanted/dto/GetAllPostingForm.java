package com.example.wanted.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetAllPostingForm {
    private Integer page;

    private Integer size;
}