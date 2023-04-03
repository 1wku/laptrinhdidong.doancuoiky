package ltdd.doan.mangxahoi.data.repository;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import ltdd.doan.mangxahoi.data.model.Post;
import ltdd.doan.mangxahoi.data.model.User;

public class PostRepository {
    private MutableLiveData<List<Post>> list_posts;
    private MutableLiveData<Post> post;

    public PostRepository() {
        list_posts = new MutableLiveData<>();
        post = new MutableLiveData<>();
    }

    public MutableLiveData<List<Post>> getList_posts(){
        list_posts = new MutableLiveData<>();
        return list_posts;
    }

    //todo hàng sài tạm chờ api rùi sửa :v
    public void getFeed(){
        User user1 = new User(1,"user email1","phone 1","phuocDZ 1","123",null,null);
        User user2 = new User(2,"user email2","phone 2","phuocDZ 2","123",null,null);
        List<Post> temp = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Post postT = new Post(i,user1,i+" ố dè "+i,null,null,"2023/4/1");
            temp.add(postT);
        }
    }


}
