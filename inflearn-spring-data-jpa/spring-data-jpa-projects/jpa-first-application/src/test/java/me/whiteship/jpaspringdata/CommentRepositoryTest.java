package me.whiteship.jpaspringdata;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    public void crud() {
        Comment comment = new Comment();
        comment.setComment("Hello Comment");
        commentRepository.save(comment);

        List<Comment> all = commentRepository.findAll();
        assertThat(all.size()).isEqualTo(1);

        long count = commentRepository.count();
        assertThat(count).isEqualTo(1L);
    }

    @Test
    public void nullCheck() {
        Optional<Comment> byId = commentRepository.findById(100L);
        assertThat(byId).isEmpty();

        List<Comment> all = commentRepository.findAll();
        assertThat(all).isEmpty();
    }

    @Test
    public void query_contains_and_greater_than() {
        Comment newComment = new Comment();
        newComment.setComment("Spring Data JPA");
        newComment.setLikeCount(100);
        commentRepository.save(newComment);

        List<Comment> comments = commentRepository.findByCommentContainsIgnoreCaseAndLikeCountGreaterThan("spring", 10);
        assertThat(comments.size()).isEqualTo(1);
    }

    @Test
    public void query_contains_order_by() {
        this.createComment("spring data jpa", 100);
        this.createComment("HIBERNATE SPRING", 55);

        List<Comment> comments = commentRepository.findByCommentContainsIgnoreCaseOrderByLikeCountDesc("spring");
        assertThat(comments.size()).isEqualTo(2);
        assertThat(comments).first().hasFieldOrPropertyWithValue("likeCount", 100);
    }

    @Test
    public void query_contains_page() {
        this.createComment("spring data jpa", 100);
        this.createComment("HIBERNATE SPRING", 55);

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "likeCount"));

        Page<Comment> comments = commentRepository.findByCommentContainsIgnoreCase("spring", pageRequest);
        assertThat(comments.getNumberOfElements()).isEqualTo(2);
        assertThat(comments).first().hasFieldOrPropertyWithValue("likeCount", 100);
    }

    private void createComment(String comment, int likeCount) {
        Comment newComment = new Comment();
        newComment.setComment("Spring Data JPA");
        newComment.setLikeCount(100);
        commentRepository.save(newComment);
    }

}