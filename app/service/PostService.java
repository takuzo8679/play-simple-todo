package service;

import model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostService {
    private final List<Post> posts = new ArrayList<>();

    public PostService() {
        posts.add(new Post(1, "my-title", "my-content"));
        posts.add(new Post(2, "study", "Play framework"));
        posts.add(new Post(3, "study", "Spring"));
    }

    public Post getPost(int postId) {
        //TODO: load from database
        Optional<Post> requestedPost = posts.stream()
                .filter(post -> post.getId() == postId)
                .findAny();
        return requestedPost.orElse(null);
    }

    public List<Post> getPosts() {
        return posts;
    }
}
