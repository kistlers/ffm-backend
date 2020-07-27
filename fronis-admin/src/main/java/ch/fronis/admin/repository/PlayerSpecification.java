package ch.fronis.admin.repository;

import ch.fronis.admin.entity.PlayerEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class PlayerSpecification implements Specification<PlayerEntity> {

    private final String query;

    public PlayerSpecification(String query) {
       this.query = query;
    }

    @Override
    public Predicate toPredicate(Root<PlayerEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (query != null) {
            Predicate firstNamePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), "%" + query.toLowerCase() + "%");
            Predicate lastNamePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), "%" + query.toLowerCase() + "%");
            predicates.add(criteriaBuilder.or(firstNamePredicate, lastNamePredicate));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}