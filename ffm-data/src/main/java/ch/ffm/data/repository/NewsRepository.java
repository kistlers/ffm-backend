package ch.ffm.data.repository;

import ch.ffm.model.entity.News;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NewsRepository
        extends JpaRepository<News, Integer>, JpaSpecificationExecutor<News> {

    List<News> findAllByOrderByPublishTimestampDesc();
}
