package com.example.wanted.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Getter
@Component
public class AesKeyConfig {
    @Value("${aeskey}")
    private String key;


}
