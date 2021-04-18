package me.whiteship.jpaspringboot;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Comment {

    @Id @GeneratedValue
    private Long id;

    private String comment;

    @ManyToOne
    private Post post;

}
