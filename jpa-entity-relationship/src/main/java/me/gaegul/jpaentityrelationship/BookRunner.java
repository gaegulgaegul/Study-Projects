package me.gaegul.jpaentityrelationship;

import lombok.RequiredArgsConstructor;
import me.gaegul.jpaentityrelationship.model.Book;
import me.gaegul.jpaentityrelationship.model.BookStore;
import me.gaegul.jpaentityrelationship.repository.BookRepository;
import me.gaegul.jpaentityrelationship.repository.BookStoreRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookRunner implements ApplicationRunner {

    private final BookRepository bookRepository;
    private final BookStoreRepository bookStoreRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        BookStore bookStore = new BookStore();
        bookStore.setName("영풍문고");
        bookStoreRepository.save(bookStore);

        Book javaBook = new Book();
        javaBook.setTitle("자바의 정석");
        bookStore.addBook(javaBook);

        Book springBook = new Book();
        springBook.setTitle("토비의 스프링");
        bookStore.addBook(springBook);

        Book jpaBook = new Book();
        jpaBook.setTitle("자바 ORM 표준 JPA 프로그래밍");
        bookStore.addBook(jpaBook);

        bookRepository.save(javaBook);
        bookRepository.save(springBook);
        bookRepository.save(jpaBook);
    }
}
