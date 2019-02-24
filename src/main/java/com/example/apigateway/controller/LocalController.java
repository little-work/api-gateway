package com.example.apigateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocalController {


    @RequestMapping("/local/hello")
    public String localRest(){
        return "zuul网关实现本地跳转功能";
    }
}
