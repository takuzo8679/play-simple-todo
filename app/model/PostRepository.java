package model;

import com.google.inject.ImplementedBy;

import java.util.List;

@ImplementedBy(PostRepositoryImpl.class)
public interface PostRepository {

    List<Post> getAll();

    Post getById(int postId);

    void save(Post post);
}
