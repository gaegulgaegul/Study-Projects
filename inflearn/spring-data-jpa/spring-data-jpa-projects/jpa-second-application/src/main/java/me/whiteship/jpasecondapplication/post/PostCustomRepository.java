package me.whiteship.jpasecondapplication.post;

import java.util.List;

public interface PostCustomRepository<T> {

    List<Post> findMyPosts();

    void delete(T t);
}
