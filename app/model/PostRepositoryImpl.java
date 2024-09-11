package model;

import jakarta.persistence.NoResultException;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import java.util.List;

public class PostRepositoryImpl implements PostRepository {

    private final JPAApi jpaApi;
    private final DbExecutionContext executionContext;

    @Inject
    public PostRepositoryImpl(JPAApi jpaApi, DbExecutionContext executionContext) {
        this.jpaApi = jpaApi;
        this.executionContext = executionContext;
    }

    @Override
    public List<Post> getAll() {
        return jpaApi.withTransaction(entityManager -> {
            return entityManager.createQuery("select p from Post p", Post.class).getResultList();
        });
    }

    @Override
    public Post getById(int postId) {
        try {
            return jpaApi.withTransaction(entityManager -> {
                return entityManager.createQuery("select p from Post p where p.id = :postId", Post.class)
                        .setParameter("postId", postId)
                        .getSingleResult();
            });
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public void save(Post post) {
        jpaApi.withTransaction(entityManager -> {
            entityManager.persist(post);
        });

    }
}
