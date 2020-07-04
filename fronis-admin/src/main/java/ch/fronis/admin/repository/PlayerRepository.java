package ch.fronis.admin.repository;

import ch.fronis.admin.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PlayerRepository extends JpaRepository<PlayerEntity, Integer>, JpaSpecificationExecutor<PlayerEntity> {
}

