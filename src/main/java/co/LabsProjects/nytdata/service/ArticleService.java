package co.LabsProjects.nytdata.service;

import co.LabsProjects.nytdata.model.Article;
import co.LabsProjects.nytdata.model.Doc;
import co.LabsProjects.nytdata.model.MostViewedResponse;
import co.LabsProjects.nytdata.model.SearchResponse;
import org.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.naming.directory.SearchResult;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    @Value("${my_api_key}")
    String apiKey;

    @Value("${most_popular_url}")
    String mostPopularUrl;

    @Value("${search_url}")
    String searchUrl;

    @Autowired
    RestTemplate restTemplate;


    public List<Article> getMostPopular(){
        MostViewedResponse response = restTemplate.getForObject(mostPopularUrl + "api-key=" + apiKey, MostViewedResponse.class);
        List<Article> results = new ArrayList<>();
        if (response != null && response.getStatus().equals("OK")) {
            results = response.getResults().stream()
                    .filter(Article::containsMedia)
                    .collect(Collectors.toList());
            results.forEach(Article::setImageUrl);
        }
        return results;
    }

    @Cacheable(value = "docs", key = "#searchTerm")
    public List<Doc> getSearchResults(String searchTerm){
        ResponseEntity<SearchResponse> searchResponse = restTemplate.getForEntity(searchUrl + searchTerm + "&api-key=" + apiKey, SearchResponse.class);
        List<Doc> results = new ArrayList<>();
        if (searchResponse.getStatusCode().equals(HttpStatus.OK) && searchResponse.getBody() != null){
            results = searchResponse.getBody().getResponse().getDocs();
            results.forEach(Doc::setImageUrl);
        }
        return results;
    }


}
