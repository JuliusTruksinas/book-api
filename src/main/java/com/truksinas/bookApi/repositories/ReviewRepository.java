package com.truksinas.bookApi.repositories;

import com.truksinas.bookApi.entities.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Integer>, JpaSpecificationExecutor<ReviewEntity> {
    @Query("SELECT AVG(r.stars) FROM ReviewEntity r WHERE r.book.id = :bookId")
    Double findRatingByBookId(@Param("bookId") Integer bookId);
}
