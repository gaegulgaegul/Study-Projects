package me.whiteship.jpasecondapplication.post;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Post extends AbstractAggregateRoot {

    @Id @GeneratedValue
    private Long id;

    private String title;

    @Lob
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public Post publish() {
        this.registerEvent(new PostPublishedEvent(this));
        return this;
    }
}
