package controllers;

import form.PostForm;
import model.Post;
import play.data.Form;
import play.data.FormFactory;
import play.data.validation.ValidationError;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.*;
import service.PostService;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    private final Form<PostForm> postForm;
    private final PostService postService;
    private final HttpExecutionContext ec;

    @Inject
    public HomeController(FormFactory formFactory, PostService postService, HttpExecutionContext ec) {
        this.postForm = formFactory.form(PostForm.class);
        this.postService = postService;
        this.ec = ec;
    }

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(views.html.index.render());
    }

    public CompletionStage<Result> getPosts(Integer postId, Http.Request request) {
        if(postId == null) {
            return postService.getPosts()
                    .thenApplyAsync(posts -> ok(views.html.posts.render(posts, postForm, request)), ec.current());
        }
        Post post = postService.getPost(postId);
        if (post == null){
            return supplyAsync(() -> ok(views.html.posts.render(Collections.emptyList(), postForm, request)), ec.current());
        }
        return supplyAsync(() -> ok(views.html.posts.render(List.of(post), postForm, request)), ec.current());
    }

    public CompletionStage<Result> createPost(Http.Request request) {
        Form<PostForm> boundForm = postForm.bindFromRequest(request);
        if(boundForm.hasErrors()) {
            return postService.getPosts()
                    .thenApplyAsync(posts ->
                            badRequest(views.html.posts.render(posts, boundForm, request)), ec.current());
        }
        postService.add(boundForm.get());
        return supplyAsync(() -> redirect(routes.HomeController.getPosts(null)), ec.current());
    }
}
