package me.whiteship.jpaspringboot;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
@Transactional
public class PostJpaRunner implements ApplicationRunner {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("JPQL >>>>>>>>>>>>>>>>>>>>>>");
        TypedQuery<Post> jpql = entityManager.createQuery("SELECT p FROM Post AS p", Post.class);
        List<Post> jpqlPosts = jpql.getResultList();
        jpqlPosts.forEach(System.out::println);

        System.out.println("Criteria >>>>>>>>>>>>>>>>>>>>>>");
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Post> criteria = builder.createQuery(Post.class);
        Root<Post> root = criteria.from(Post.class);
        criteria.select(root);

        List<Post> criteriaPosts = entityManager.createQuery(criteria).getResultList();
        criteriaPosts.forEach(System.out::println);

        System.out.println("Native >>>>>>>>>>>>>>>>>>>>>>");
        List<Post> nativePosts = entityManager.createNativeQuery("select * from post", Post.class)
                .getResultList();
        nativePosts.forEach(System.out::println);
    }

}
