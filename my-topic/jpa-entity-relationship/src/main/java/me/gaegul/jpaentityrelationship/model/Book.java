package me.gaegul.jpaentityrelationship.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Book {

    @Id @GeneratedValue
    private Long id;

    private String title;

    @ManyToOne
    private BookStore bookStore;

}
