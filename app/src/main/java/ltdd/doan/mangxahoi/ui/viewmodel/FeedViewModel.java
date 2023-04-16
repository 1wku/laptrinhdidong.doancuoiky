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
public class FeedViewModel extends ViewModel {
    public FeedViewModel() {
    }

    public List<Post> getFeed(){
        User user = new User();
        List<Post> temp = new ArrayList<>();
        Post post = new Post();
        for (int i = 0; i < 20; i++) {
            temp.add(post.getEx(user.getEx()));
        }
        return temp;
    }
}