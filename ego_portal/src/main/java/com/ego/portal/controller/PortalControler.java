package com.ego.portal.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PortalControler {

    @RequestMapping("/")
    public String welcome() {
        return "index";
    }
}
