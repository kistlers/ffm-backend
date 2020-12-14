package ch.ffm.data.repository;

import ch.ffm.model.entity.Player;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PlayerRepository
        extends JpaRepository<Player, Integer>, JpaSpecificationExecutor<Player> {

    List<Player> findAllByOrderByPlayerNumberAsc();

    Page<Player> findAllByFirstNameEqualsIgnoreCaseAndLastNameEqualsIgnoreCaseOrderByPlayerNumberAsc(
            String firstNameLike, String lastNameLike, Pageable pageable);
}
