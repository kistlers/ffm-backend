package ch.fronis.data.repository;

import ch.fronis.model.entity.Sponsor;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SponsorRepository
        extends JpaRepository<Sponsor, Integer>, JpaSpecificationExecutor<Sponsor> {

    List<Sponsor> findAllByOrderByOrderingAsc();

    Page<Sponsor> findAllByOrderByOrderingAsc(Pageable pageable);
}
