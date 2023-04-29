package ltdd.doan.mangxahoi.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;


import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import ltdd.doan.mangxahoi.data.dto.response.LikePostResponse;
import ltdd.doan.mangxahoi.data.model.Post;
import ltdd.doan.mangxahoi.data.model.User;
import ltdd.doan.mangxahoi.data.repository.PostRepository;
import ltdd.doan.mangxahoi.interfaces.OnGetPostResult;
import ltdd.doan.mangxahoi.interfaces.OnLikePostResult;

@HiltViewModel
public class FeedViewModel extends ViewModel {
    private PostRepository pRepo;

    private MutableLiveData<List<Post>> posts;
    private MutableLiveData<String> message;

    @Inject
    public FeedViewModel(PostRepository pRepo) {
        this.pRepo = pRepo;

        posts = pRepo.getPosts();
        message = pRepo.getMessage();
    }

    public MutableLiveData<List<Post>> getPosts() {
        return posts;
    }


    public MutableLiveData<String> getMessage() {
        return message;
    }

    public void getFeed() {
        pRepo.getFeed(1,10,new OnGetPostResult() {
            @Override
            public void onSuccess() {
                posts = pRepo.getPosts();
                message.setValue("Đã lấy thành công dữ liệu");

            }

            @Override
            public void onError() {
                System.out.println("Error : ");
                message.setValue("Lõi trong quá trình lấy feed");
            }
        });
    }

    public void updatePost(Post post) {
        pRepo.updatePost(post);
    }

    public void deletePost(String post_id) {
        pRepo.deletePost(post_id);
    }

    public void like(String post_id, OnLikePostResult onLikePostResult) {
        pRepo.like(post_id,onLikePostResult);
    }

}