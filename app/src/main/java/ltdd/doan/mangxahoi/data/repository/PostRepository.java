package ltdd.doan.mangxahoi.data.repository;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import ltdd.doan.mangxahoi.api.ApiInterface;
import ltdd.doan.mangxahoi.data.model.Comment;
import ltdd.doan.mangxahoi.data.model.Post;
import ltdd.doan.mangxahoi.session.Session;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostRepository {
    private final ApiInterface apiService;
    private MutableLiveData<List<Post>> posts;
    private MutableLiveData<Post> post;
    private MutableLiveData<String> message; // messages from response
    private MutableLiveData<Boolean> status; // status for navigation
    public PostRepository(ApiInterface apiService) {
        this.apiService = apiService;

        posts = new MutableLiveData<>();
        post = new MutableLiveData<>();
        message = new MutableLiveData<>();
        status = new MutableLiveData<>();
    }
    public MutableLiveData<List<Post>> getPosts() {
        posts = new MutableLiveData<>();
        return posts;
    }
    public MutableLiveData<Post> getPost() {
        post = new MutableLiveData<>();
        return post;
    }

    public MutableLiveData<String> getMessage() {
        message = new MutableLiveData<>();
        return message;
    }

    public MutableLiveData<Boolean> getStatus() {
        status = new MutableLiveData<>();
        return status;
    }

    // TODO: 4/18/2023
    public void getFeed(){
        apiService.getPostTimelineByUser(Session.ACTIVE_USER.getId()).enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(response.code()==200) {
                    posts.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
            }
        });
    }

    // TODO: 4/18/2023
    public void getPostsByUserId(String user_id){
//        List<Post> temp = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            temp.add(new Post().getEx(new User().getEx(user_id)));
//        }
//        posts.setValue(temp);
    }

    // TODO: 4/18/2023
    public void createPost(){

    }

    // TODO: 4/18/2023
    public void updatePost(Post post){

    }

    // TODO: 4/18/2023
    public void deletePost(String post_id){

    }

    // TODO: 4/18/2023
    public void like(String post_id){

    }

    // TODO: 4/18/2023
    public void unlike(String post_id){

    }

    // TODO: 4/18/2023
    public void createComment(String post_id, String text){

    }

    // TODO: 4/18/2023
    public void updateComment(Comment comment){

    }

    // TODO: 4/18/2023
    public void deleteComment(String comment_id){

    }
}



