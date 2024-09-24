package com.truksinas.bookApi.repositories;

import com.truksinas.bookApi.entities.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Integer> {
}
