package com.truksinas.bookApi.specifications;

import com.truksinas.bookApi.entities.ReviewEntity;
import org.springframework.data.jpa.domain.Specification;

public class ReviewSpecification {
    public static Specification<ReviewEntity> hasStars(Integer stars) {
        return (root, query, criteriaBuilder) ->
                stars == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("stars"), stars);
    }

    public static Specification<ReviewEntity> hasBookId(Integer bookId) {
        return (root, query, criteriaBuilder) ->
                bookId == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("book").get("id"), bookId);
    }
}