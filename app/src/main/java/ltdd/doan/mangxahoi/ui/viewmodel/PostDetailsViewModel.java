package ltdd.doan.mangxahoi.ui.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.Closeable;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import ltdd.doan.mangxahoi.data.dto.response.LikePostResponse;
import ltdd.doan.mangxahoi.data.model.Comment;
import ltdd.doan.mangxahoi.data.model.Post;
import ltdd.doan.mangxahoi.data.model.User;
import ltdd.doan.mangxahoi.data.repository.PostRepository;
import ltdd.doan.mangxahoi.interfaces.OnGetPostByIdResult;
import ltdd.doan.mangxahoi.interfaces.OnLikePostResult;

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

    public void getPostDetailsById(String post_id) {
        pRepo.getPostDetailsById(post_id, new OnGetPostByIdResult() {
            @Override
            public void onSuccess(Post resultPost) {
                post.setValue(resultPost);
                System.out.println("Get post by id ");

            }

            @Override
            public void onError(String error) {
                Log.e("Get post by ID", error);
            }
        });
    }

    public void updatePost(Post post) {
        pRepo.updatePost(post);
    }

    public void deletePost(String post_id) {
        pRepo.deletePost(post_id);
    }

    public void like(String post_id) {
        pRepo.like(post_id, new OnLikePostResult() {
            @Override
            public void onSuccess(LikePostResponse result) {
                System.out.println(result);
                getPost();
            }

            @Override
            public void onError(String error) {
                System.out.println(error);


            }
        });
    }


    public void createComment(String post_id, String text) {
        pRepo.createComment(post_id, text);
    }

    public void updateComment(Comment comment) {
        pRepo.updateComment(comment);
    }

    public void deleteComment(String comment_id) {
        pRepo.deleteComment(comment_id);
    }
}