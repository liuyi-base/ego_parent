package com.ego.search.controller;


import com.ego.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SearchController {
    @Autowired
    private SearchService searchService;

    @RequestMapping("/insert")
    @ResponseBody
    public int insert(long[] id) {
        return searchService.insert(id);
    }
}
