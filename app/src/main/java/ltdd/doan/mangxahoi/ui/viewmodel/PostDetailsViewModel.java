package ltdd.doan.mangxahoi.ui.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.Closeable;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import ltdd.doan.mangxahoi.data.model.Comment;
import ltdd.doan.mangxahoi.data.model.Post;
import ltdd.doan.mangxahoi.data.model.User;
import ltdd.doan.mangxahoi.data.repository.PostRepository;
@HiltViewModel
public class PostDetailsViewModel extends ViewModel {
    private PostRepository pRepo;

    private MutableLiveData<Post> post;
    private MutableLiveData<String> message;
    private MutableLiveData<Boolean> status;

    @Inject
    public PostDetailsViewModel(PostRepository pRepo) {
        this.pRepo = pRepo;

        post = pRepo.getPost();
        message = pRepo.getMessage();
        status = pRepo.getStatus();
    }
    public MutableLiveData<Post> getPost() {
        return post;
    }

    public MutableLiveData<String> getMessage() {
        return message;
    }

    public MutableLiveData<Boolean> getStatus() {
        return status;
    }

    public void getPostDetailsById(int post_id) {
        pRepo.getPostsByUserId(post_id);
    }

    public void updatePost(Post post) {
        pRepo.updatePost(post);
    }

    public void deletePost(int post_id) {
        pRepo.deletePost(post_id);
    }

    public void like(int post_id) {
        pRepo.like(post_id);
    }

    public void unlike(int post_id) {
        pRepo.un_like(post_id);
    }

    public void createComment(int post_id, String text) {
        pRepo.createComment(post_id, text);
    }

    public void updateComment(Comment comment) {
        pRepo.updateComment(comment);
    }

    public void deleteComment(int comment_id) {
        pRepo.deleteComment(comment_id);
    }
}