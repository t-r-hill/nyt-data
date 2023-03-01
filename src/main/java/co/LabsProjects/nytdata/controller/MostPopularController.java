package co.LabsProjects.nytdata.controller;


import co.LabsProjects.nytdata.model.Article;
import co.LabsProjects.nytdata.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MostPopularController {

    @Autowired
    ArticleService articleService;

    @GetMapping("/most-popular-test")
    public List<Article> getMostPopular(){
        return articleService.getMostPopular();

    }
}
