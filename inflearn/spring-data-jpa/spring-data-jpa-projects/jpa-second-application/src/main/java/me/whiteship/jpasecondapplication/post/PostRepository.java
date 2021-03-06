package me.whiteship.jpasecondapplication.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface PostRepository extends JpaRepository<Post, Long>, PostCustomRepository<Post>, QuerydslPredicateExecutor<Post> {
}
