package me.whiteship.jpawebapplication.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void crud() {
        Post post = new Post();
        post.setTitle("jpa");
        Post savedPost = postRepository.save(post);
        assertThat(entityManager.contains(post)).isTrue();
        assertThat(entityManager.contains(savedPost)).isTrue();
        assertThat(post == savedPost).isTrue();

        Post postUpdate = new Post();
        postUpdate.setId(post.getId());
        postUpdate.setTitle("hibernate");
        Post updatedPost = postRepository.save(postUpdate);
        assertThat(entityManager.contains(updatedPost)).isTrue();
        assertThat(entityManager.contains(postUpdate)).isFalse();
        assertThat(postUpdate != updatedPost).isTrue();

        List<Post> all = postRepository.findAll();
        assertThat(all.size()).isEqualTo(1);
    }

    @Test
    public void findByTitleStartingWith() {
        createdPost();

        List<Post> spring = postRepository.findByTitleStartingWith("spring");
        assertThat(spring.size()).isEqualTo(1);
    }

    private Post createdPost() {
        Post post = new Post();
        post.setTitle("spring data jpa");
        return postRepository.save(post);
    }

    @Test
    public void findByTitle() {
        createdPost();

        List<Post> byTitle = postRepository.findByTitle("spring data jpa", Sort.by("title"));
        assertThat(byTitle.size()).isEqualTo(1);
    }

    @Test
    public void updateTitle() {
        Post post = createdPost();

        String hibernate = "hibernate";
        int update = postRepository.updateTitle(hibernate, post.getId());

        Optional<Post> byId = postRepository.findById(post.getId());
        assertThat(byId.get().getTitle()).isEqualTo(hibernate);
    }

}