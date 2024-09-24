package com.truksinas.bookApi.specifications;

import com.truksinas.bookApi.entities.BookEntity;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {
    public static Specification<BookEntity> hasTitle(String title) {
        return (root, query, criteriaBuilder) ->
                title == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("title"), title);
    }

    public static Specification<BookEntity> hasAuthor(String author) {
        return (root, query, criteriaBuilder) ->
                author == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("author"), author);
    }

    public static Specification<BookEntity> hasReleaseYear(Integer releaseYear) {
        return (root, query, criteriaBuilder) ->
                releaseYear == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("releaseYear"), releaseYear);
    }

    public static Specification<BookEntity> hasRating(Double rating) {
        return (root, query, criteriaBuilder) ->
                rating == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("rating"), rating);
    }
}