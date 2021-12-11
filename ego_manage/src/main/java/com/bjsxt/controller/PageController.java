package com.bjsxt.controller;


import com.ego.commons.pojo.EgoResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PageController {


    @RequestMapping("/")
    public String login() {
        System.out.println("已经进入");
        return "login";
    }

    @RequestMapping("/main")
    public String showIndex() {
        return "index";
    }

    @RequestMapping("/loginSuccess")
    @ResponseBody
    public EgoResult loginSuccess() {
        return EgoResult.ok();
    }

    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page) {
        return page;
    }

    @RequestMapping("/rest/page/item-edit")
    public String showEdit() {
        return "item-edit";
    }
}
