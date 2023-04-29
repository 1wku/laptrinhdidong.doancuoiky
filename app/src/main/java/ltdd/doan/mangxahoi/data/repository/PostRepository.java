package ltdd.doan.mangxahoi.data.repository;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import ltdd.doan.mangxahoi.api.ApiInterface;
import ltdd.doan.mangxahoi.data.dto.response.ListFeedResponse;
import ltdd.doan.mangxahoi.data.dto.response.SuccessfullResponse;
import ltdd.doan.mangxahoi.data.model.Comment;
import ltdd.doan.mangxahoi.data.model.Post;
import ltdd.doan.mangxahoi.interfaces.OnGetPostByIdResult;
import ltdd.doan.mangxahoi.interfaces.OnGetPostResult;
import ltdd.doan.mangxahoi.interfaces.OnGetPostsByUserResult;
import ltdd.doan.mangxahoi.session.Session;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostRepository {
    private final ApiInterface apiService;
    private MutableLiveData<List<Post>> posts;
    private MutableLiveData<Post> post;
    private final Context context;
    private MutableLiveData<String> message; // messages from response
    private MutableLiveData<Boolean> status; // status for navigation
    private UserRepository userRepository;
    public PostRepository(Context context,ApiInterface apiService) {
        this.apiService = apiService;
        this.context = context ;
        posts = new MutableLiveData<>();
        post = new MutableLiveData<>();
        message = new MutableLiveData<>();
        status = new MutableLiveData<>();
        userRepository = new UserRepository(context, apiService);
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
    public void getFeed( Integer page , Integer limit ,OnGetPostResult onGetPostResult){
        String userId = Session.getSharedPreference(context, "user_id", "");
        apiService.getPostTimelineByUser(userId,page,limit ).enqueue(new Callback<ListFeedResponse>() {
            @Override
            public void onResponse(Call<ListFeedResponse> call, Response<ListFeedResponse> response) {
                System.out.println(response);

                if(response.code()==200) {
                    posts.setValue(response.body().timeline);
                    onGetPostResult.onSuccess();
                }
                else onGetPostResult.onError();
            }
            @Override
            public void onFailure(Call<ListFeedResponse> call, Throwable t) {
                System.out.println(t.getMessage());
                onGetPostResult.onError();
            }
        });
    }

    // TODO: 4/18/2023
    public void getPostsByUserId(String user_id, OnGetPostsByUserResult onGetPostsByUserResult){
        apiService.getPostsByUserId(user_id).enqueue(new Callback<SuccessfullResponse<List<Post>>>() {
            @Override
            public void onResponse(Call<SuccessfullResponse<List<Post>>> call, Response<SuccessfullResponse<List<Post>>> response) {
                if(response.code()==200) {
                    onGetPostsByUserResult.onSuccess(response.body().data);
                }
                else onGetPostsByUserResult.onError(response.message());
            }
            @Override
            public void onFailure(Call<SuccessfullResponse<List<Post>>> call, Throwable t) {
                System.out.println(t.getMessage());
                onGetPostsByUserResult.onError(t.getMessage());
            }
        });
    }
    public void getPostDetailsById(String post_id , OnGetPostByIdResult onGetPostByIdResult){
        System.out.println("Đang lấy post detail");
        apiService.getPost(post_id).enqueue(new Callback<SuccessfullResponse<Post>>() {
            @Override
            public void onResponse(Call<SuccessfullResponse<Post>> call, Response<SuccessfullResponse<Post>> response) {
                System.out.println(response);
                if(response.code()==200) {
                    onGetPostByIdResult.onSuccess(response.body().data);
                }
                else onGetPostByIdResult.onError(response.message());
            }
            @Override
            public void onFailure(Call<SuccessfullResponse<Post>> call, Throwable t) {
                System.out.println(t.getMessage());
                onGetPostByIdResult.onError(t.getMessage());
            }
        });
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



