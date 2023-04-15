package ltdd.doan.mangxahoi.ui.viewmodel;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import ltdd.doan.mangxahoi.data.model.Post;
import ltdd.doan.mangxahoi.data.model.User;

public class ProfileViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    public User getUserByID(@Nullable int user_id){
        User user = new User().getEx();
        return user;
    }

    public List<Post> getPostByUserID(){
        User user = new User();
        List<Post> temp = new ArrayList<>();
        Post post = new Post();
        for (int i = 0; i < 20; i++) {
            temp.add(post.getEx(user.getEx()));
        }
        return temp;
    }}