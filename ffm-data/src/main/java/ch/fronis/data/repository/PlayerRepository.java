package ch.fronis.data.repository;

import ch.fronis.model.entity.Player;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PlayerRepository
        extends JpaRepository<Player, Integer>, JpaSpecificationExecutor<Player> {

    List<Player> findAllByOrderByPlayerNumberAsc();
}
