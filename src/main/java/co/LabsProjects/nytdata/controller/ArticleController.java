package co.LabsProjects.nytdata.controller;

import co.LabsProjects.nytdata.service.ArticleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.cache.CacheManager;

@Controller
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @Autowired
    CacheManager cacheManager;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("articleList", articleService.getMostPopular());
        return "index";
    }

    @PostMapping("/search")
    public String getSearchResults(@RequestParam(name = "query") String searchTerm, Model model){
        model.addAttribute(articleService.getSearchResults(searchTerm));
        return "search-results";
    }

    @GetMapping("/search")
    public String showSearchPage(Model model){
        return "search";
    }
}
