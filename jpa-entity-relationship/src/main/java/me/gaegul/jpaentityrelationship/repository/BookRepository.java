package me.gaegul.jpaentityrelationship.repository;

import me.gaegul.jpaentityrelationship.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
