package ltdd.doan.mangxahoi.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import ltdd.doan.mangxahoi.data.model.Post;
import ltdd.doan.mangxahoi.data.model.User;
import ltdd.doan.mangxahoi.data.repository.PostRepository;
import ltdd.doan.mangxahoi.data.repository.UserRepository;
import ltdd.doan.mangxahoi.interfaces.OnGetCheckIsFollowUserResult;
import ltdd.doan.mangxahoi.interfaces.OnGetPostsByUserResult;
import ltdd.doan.mangxahoi.interfaces.OnGetUserDetailResult;
import ltdd.doan.mangxahoi.interfaces.OnToogleFollowResult;

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
        uRepo.getUserDetailsById(user_id, new OnGetUserDetailResult() {
            @Override
            public void onSuccess(User result) {
                user.setValue(result);
            }
            @Override
            public void onError(String error) {
                System.out.println(error);
            }
        });
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

    public void onCheckIsFollowUser(String user_id, OnGetCheckIsFollowUserResult onGetCheckIsFollowUserResult) {
        uRepo.checkIsFollowUser(user_id,onGetCheckIsFollowUserResult);


    }

    public void follow(String user_id, OnToogleFollowResult onToogleFollowResult) {
        uRepo.toggleFollow(user_id,onToogleFollowResult);
    }


}