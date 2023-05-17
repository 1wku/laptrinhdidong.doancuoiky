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
import ltdd.doan.mangxahoi.data.repository.UserRepository;
import ltdd.doan.mangxahoi.interfaces.OnCreateCommentResult;
import ltdd.doan.mangxahoi.interfaces.OnDeletePostResult;
import ltdd.doan.mangxahoi.interfaces.OnGetCommentResult;
import ltdd.doan.mangxahoi.interfaces.OnGetPostByIdResult;
import ltdd.doan.mangxahoi.interfaces.OnGetUserDetailResult;
import ltdd.doan.mangxahoi.interfaces.OnLikePostResult;
import ltdd.doan.mangxahoi.interfaces.OnUpdatePostResult;

@HiltViewModel
public class PostDetailsViewModel extends ViewModel {
    private PostRepository pRepo;
    private UserRepository uRepo;

    private MutableLiveData<Post> post;
    private MutableLiveData<List<Comment>> comments;
    private MutableLiveData<String> message;
    private MutableLiveData<Boolean> status;

    @Inject
    public PostDetailsViewModel(PostRepository pRepo, UserRepository uRepo) {
        this.pRepo = pRepo;
        this.uRepo  = uRepo;
        post = pRepo.getPost();
        message = pRepo.getMessage();
        status = pRepo.getStatus();
        comments = pRepo.getComments();
    }
    public MutableLiveData<String> getMessage() {
        return message;
    }

    public MutableLiveData<Post> getPost() {
        return pRepo.getPost();
    }
    public MutableLiveData<List<Comment>> getComments() {
        return pRepo.getComments();
    }

    public void getCurrentUser(OnGetUserDetailResult onGetUserDetailResult){
        uRepo.getCurrentUserInfo(onGetUserDetailResult);
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
        pRepo.updatePost(post, new OnUpdatePostResult() {
            @Override
            public void onSuccess(Post result) {

            }

            @Override
            public void onError(String error) {

            }
        });
    }

    public void deletePost(String post_id) {
        pRepo.deletePost(post_id, new OnDeletePostResult() {
            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onError(String error) {

            }
        });
    }

    public void like(String post_id, OnLikePostResult onLikePostResult) {
        pRepo.like(post_id,onLikePostResult );
    }


    public void createComment(String post_id, String text, OnCreateCommentResult onCreateCommentResult) {
        pRepo.createComment(post_id, text, onCreateCommentResult);
    }
    public void getCommentsByPost(String post_id) {
        pRepo.getCommentFromPost(post_id, new OnGetCommentResult() {
            @Override
            public void onSuccess(List<Comment> result) {
                comments.setValue(result);
            }

            @Override
            public void onError(String error) {
                System.out.println(error);

            }
        });
    }

    public void updateComment(Comment comment) {
        pRepo.updateComment(comment);
    }

    public void deleteComment(String comment_id) {
        pRepo.deleteComment(comment_id);
    }
}