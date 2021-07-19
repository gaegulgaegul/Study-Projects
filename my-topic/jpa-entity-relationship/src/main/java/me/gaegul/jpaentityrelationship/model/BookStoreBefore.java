package me.gaegul.jpaentityrelationship.model;

import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

//@Entity
@Setter
@Getter
public class BookStoreBefore {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "bookStore")
    private Set<Book> books = new HashSet<>();

    public void addBook(Book book) {
        this.getBooks().add(book);
    }

}
