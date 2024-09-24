package com.truksinas.bookApi.repositories;

import com.truksinas.bookApi.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {
}
