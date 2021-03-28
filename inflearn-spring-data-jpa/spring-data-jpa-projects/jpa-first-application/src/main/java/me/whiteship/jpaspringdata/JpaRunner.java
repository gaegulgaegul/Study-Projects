package me.whiteship.jpaspringdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class JpaRunner implements ApplicationRunner {

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Post post = new Post();
        post.setTitle("Spring Data Jpa");

        Comment comment1 = new Comment();
        comment1.setComment("comment1");

        Comment comment2 = new Comment();
        comment2.setComment("comment2");

        post.addComment(comment1);
        post.addComment(comment2);

        postRepository.save(post);
    }
}
