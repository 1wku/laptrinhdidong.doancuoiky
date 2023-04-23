package ltdd.doan.mangxahoi.api;

import java.util.List;

import ltdd.doan.mangxahoi.data.dto.request.LikePostRequest;
import ltdd.doan.mangxahoi.data.dto.request.LoginRequest;
import ltdd.doan.mangxahoi.data.dto.request.RegisterRequest;
import ltdd.doan.mangxahoi.data.dto.response.LikePostResponse;
import ltdd.doan.mangxahoi.data.model.Post;
import ltdd.doan.mangxahoi.data.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {

    // Authenticaiton

    @POST("api/auth/register")
    Call<User> register(@Body RegisterRequest request);
    @POST("api/auth/login")
    Call<User> login(@Body LoginRequest request);


    //user
    @GET("api/users/search/user")
    Call<List<User>> getAllUser();

    // post
    @POST("api/posts")
    Call<Post> createPost(@Body Post request);
    @POST("api/posts/{id}")
    Call<Post> updatePost( @Body Post request,@Path("id") String id);
    @DELETE("api/posts/{id}")
    Call<String> deletePost(@Path("id") String id);
    @PUT("api/posts/{id}/like")
    Call<LikePostResponse> likePost(@Body LikePostRequest request, @Path("id") String id);

    @GET("api/posts/{id}/save")
    Call<LikePostResponse> savePost(@Body Post request, @Path("id") String id);
    @GET("api/posts/all/{userId}")
    Call<List<User>> getPostsByUserId(@Path("userId") String userId);
    @GET("api/posts/timeline/{userId}")
    Call<List<User>> getPostTimelineByUser(@Path("userId") String userId);
}
