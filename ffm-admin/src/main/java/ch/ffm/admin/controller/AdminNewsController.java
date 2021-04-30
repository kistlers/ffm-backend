package ch.ffm.admin.controller;

import ch.ffm.data.repository.NewsRepository;
import ch.ffm.data.repository.PlayerRepository;
import ch.ffm.model.entity.News;
import ch.ffm.model.entity.Player;
import ch.ffm.model.reactadmin.DeletedResponse;
import com.mysql.cj.jdbc.exceptions.PacketTooBigException;
import java.text.MessageFormat;
import java.util.List;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/news")
public class AdminNewsController {

    private static final Logger logger = LoggerFactory.getLogger(AdminNewsController.class);

    private final NewsRepository newsRepository;

    public AdminNewsController(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<News>> allNews(@RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer perPage,
            @RequestParam(required = false, defaultValue = "id") String field,
            @RequestParam(required = false, defaultValue = "DESC") Sort.Direction order) {
        final var pageRequest = PageRequest.of(page, perPage, Sort.by(order, field));
        final var result = newsRepository.findAll(pageRequest);
        return ResponseEntity.ok()
                .header("Content-Range", perPage + "/" + result.getTotalElements())
                .body(result.getContent());
    }

    @GetMapping("/{newsId}")
    public News oneNews(@PathVariable Integer newsId) {
        return newsRepository.findById(newsId).orElseThrow(
                () -> new RuntimeException(MessageFormat.format(
                        "Cannot handle news GET request. News with id={0} not found", newsId))
        );
    }

    @PostMapping({"", "/"})
    public News newNews(@RequestBody News newNews) {
        return newsRepository.save(newNews);
    }

    @PutMapping("/{newsId}")
    public News replaceNews(@RequestBody News newNews, @PathVariable Integer newsId)
            throws PacketTooBigException {
        try {
            return newsRepository.findById(newsId)
                    .map(news -> {
                        news.setTitle(newNews.getTitle());
                        news.setText(newNews.getText());
                        news.setPublishTimestamp(newNews.getPublishTimestamp());
                        news.setImage(newNews.getImage());
                        return newsRepository.save(news);
                    })
                    .orElseThrow(() -> new RuntimeException(MessageFormat.format(
                            "Cannot handle news PUT request. News with id={0} not found", newsId)));
        } catch (JpaSystemException e) {
            if (PacketTooBigException.class.equals(e.getCause().getClass())) {
                logger.info(MessageFormat.format(
                        "Max image size exceeded. Upload a smaller image. (newsId={0})", newsId));
                throw new PacketTooBigException("Max image size exceeded. Upload a smaller image.");
            }
            throw e;
        }
    }

    @DeleteMapping("/{newsId}")
    public ResponseEntity<DeletedResponse> deleteNews(@PathVariable Integer newsId) {
        newsRepository.deleteById(newsId);
        return ResponseEntity.ok().body(new DeletedResponse(newsId));
    }
}
