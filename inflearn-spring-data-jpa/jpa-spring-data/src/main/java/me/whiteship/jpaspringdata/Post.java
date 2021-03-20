package me.whiteship.jpaspringdata;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Post {

    @Id @GeneratedValue
    private Long id;

    private String title;

    @OneToMany(mappedBy = "post")
    private Set<Comment> comments = new HashSet<>();

    public void addComment(Comment comment) {
        comment.setPost(this);
        this.getComments().add(comment);
    }

}
