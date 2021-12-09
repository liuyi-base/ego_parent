package com.bjsxt.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PageController {


    @RequestMapping("/")
    public String login(){
        System.out.println("已经进入");
        return "login";
    }
}
