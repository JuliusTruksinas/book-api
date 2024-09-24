package com.truksinas.bookApi.repositories;

import com.truksinas.bookApi.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BookRepository extends JpaRepository<BookEntity, Integer>, JpaSpecificationExecutor<BookEntity> {
}
