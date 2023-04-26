package ltdd.doan.mangxahoi.ui.viewmodel;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import ltdd.doan.mangxahoi.data.model.Post;
import ltdd.doan.mangxahoi.data.model.User;
import ltdd.doan.mangxahoi.data.repository.PostRepository;
import ltdd.doan.mangxahoi.data.repository.UserRepository;
import ltdd.doan.mangxahoi.interfaces.OnGetPostsByUserResult;

@HiltViewModel
public class ProfileViewModel extends ViewModel {
    private UserRepository uRepo;
    private PostRepository pRepo;
    private MutableLiveData<User> user;
    private MutableLiveData<List<Post>> posts;

    @Inject
    public ProfileViewModel(UserRepository uRepo, PostRepository pRepo) {
        this.uRepo = uRepo;
        this.pRepo = pRepo;

        user = uRepo.getUser();
        posts = pRepo.getPosts();
    }

    public MutableLiveData<User> getUser() {
        return user;
    }

    public MutableLiveData<List<Post>> getPosts() {
        return posts;
    }

    public void getUserDetailsById(String user_id) {
        uRepo.getUserDetailsById(user_id);
    }

    public void getPostsByUserId(String user_id) {
        pRepo.getPostsByUserId(user_id, new OnGetPostsByUserResult() {
            @Override
            public void onSuccess(List<Post> result) {
                posts.setValue(result);
            }

            @Override
            public void onError(String error) {
                System.out.println(error);
            }
        });
    }

    public void follow(String user_id) {
        uRepo.follow(user_id);
    }

    public void unfollow(String user_id) {
        uRepo.unfollow(user_id);
    }
}