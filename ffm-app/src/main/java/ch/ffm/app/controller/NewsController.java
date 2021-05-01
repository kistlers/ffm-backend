package ch.ffm.app.controller;

import ch.ffm.app.config.HeaderHelper;
import ch.ffm.data.repository.NewsRepository;
import ch.ffm.model.entity.News;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/news")
public class NewsController {

    private final NewsRepository newsRepository;

    public NewsController(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    /**
     * @return news ordered by publishTimestamp
     */
    @GetMapping({"", "/"})
    public ResponseEntity<List<News>> news() {
        var news = newsRepository.findAllByOrderByCreatedAtDesc();
        return HeaderHelper.createOKResponseEntityDefaultCacheControl(news);
    }
}
