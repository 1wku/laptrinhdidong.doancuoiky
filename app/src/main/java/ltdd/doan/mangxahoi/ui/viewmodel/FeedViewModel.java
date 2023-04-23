package ltdd.doan.mangxahoi.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;


import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import ltdd.doan.mangxahoi.data.model.Post;
import ltdd.doan.mangxahoi.data.model.User;
import ltdd.doan.mangxahoi.data.repository.PostRepository;
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
        pRepo.getFeed();
    }

    public void updatePost(Post post) {
        pRepo.updatePost(post);
    }

    public void deletePost(String post_id) {
        pRepo.deletePost(post_id);
    }

    public void like(String post_id) {
        pRepo.like(post_id);
    }

    public void unlike(String post_id) {
        pRepo.unlike(post_id);
    }
}