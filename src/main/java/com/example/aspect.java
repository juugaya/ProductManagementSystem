package com.example;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class aspect {

    @Before("execution(* com.example.demo.service.ProductService.searchProducts(..))")
    public void beforeSearch() {
        System.out.println("検索を開始します...");
    }

    @After("execution(* com.example.demo.service.ProductService.searchProducts(..))")
    public void afterSearch() {
        System.out.println("検索が完了しました。");
    }
}

