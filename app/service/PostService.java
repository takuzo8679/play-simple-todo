package service;

import form.PostForm;
import model.Post;
import model.PostRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionStage;

public class PostService {
    private final PostRepository postRepository;
    private final List<Post> posts = new ArrayList<>();

    @Inject
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public CompletionStage<Post> getPost(int postId) {
        return postRepository.getById(postId);
    }

    public CompletionStage<List<Post>> getPosts() {
        return postRepository.getAll();
    }

    public void add(PostForm postForm) {
        postRepository.save(new Post(postForm.getTitle(), postForm.getContent()));
    }
}
