
package com.ego.search;

import com.ego.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {
    @Autowired
    private SearchService searchService;

    @RequestMapping("search.html")
    public String showSearch(String q, Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "12") int size) {
        model.addAllAttributes(searchService.search(q, page, size));
        return "search";
    }
}