package ch.fronis.data.repository;

import ch.fronis.model.entity.Player;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;

public class PlayerSpecification implements Specification<Player> {

    private final Logger logger = LoggerFactory.getLogger(PlayerSpecification.class);

    private final String query;

    public PlayerSpecification(String query) {
        this.query = query;
    }

    @Override
    public Predicate toPredicate(
            Root<Player> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (query != null && !query.isEmpty()) {
            logger.info("query players by: " + query);
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(
                    criteriaBuilder.like(
                            criteriaBuilder.lower(root.get("firstName")),
                            "%" + query.toLowerCase() + "%"));
            predicates.add(
                    criteriaBuilder.like(
                            criteriaBuilder.lower(root.get("lastName")),
                            "%" + query.toLowerCase() + "%"));
            return criteriaBuilder.or(predicates.toArray(Predicate[]::new));
        }
        return null;
    }
}