package me.gaegul.jpaentityrelationship.repository;

import me.gaegul.jpaentityrelationship.model.BookStore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookStoreRepository extends JpaRepository<BookStore, Long> {
}
