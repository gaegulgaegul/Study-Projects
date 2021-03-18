package me.whiteship.jpaspringboot;

import org.hibernate.Session;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Transactional
public class PostHibernateRunner implements ApplicationRunner {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Session session = entityManager.unwrap(Session.class);

        Post post = session.get(Post.class, 4L);
        System.out.println("========================");
        System.out.println(post.getTitle());

        post.getComments().forEach(c -> {
            System.out.println("---------------------");
            System.out.println(c.getComment());
        });

    }
}
