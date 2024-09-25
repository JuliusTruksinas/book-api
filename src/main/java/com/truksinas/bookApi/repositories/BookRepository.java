package com.truksinas.bookApi.repositories;

import com.truksinas.bookApi.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface BookRepository extends JpaRepository<BookEntity, Integer>, JpaSpecificationExecutor<BookEntity> {
    @Modifying
    @Transactional
    @Query("UPDATE BookEntity b SET b.rating = :rating WHERE b.id = :bookId")
    void updateBookRating(Integer bookId, Double rating);
}
