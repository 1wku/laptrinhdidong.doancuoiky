package ltdd.doan.mangxahoi.api;

import java.util.List;

import ltdd.doan.mangxahoi.data.dto.request.CreateConversationRequest;
import ltdd.doan.mangxahoi.data.dto.request.FollowUserRequest;
import ltdd.doan.mangxahoi.data.dto.request.LikePostRequest;
import ltdd.doan.mangxahoi.data.dto.request.LoginRequest;
import ltdd.doan.mangxahoi.data.dto.request.RegisterRequest;
import ltdd.doan.mangxahoi.data.dto.request.SendMessageRequest;
import ltdd.doan.mangxahoi.data.dto.response.LikePostResponse;
import ltdd.doan.mangxahoi.data.dto.response.ListFeedResponse;
import ltdd.doan.mangxahoi.data.dto.response.ListMessageResponse;
import ltdd.doan.mangxahoi.data.dto.response.SuccessfullResponse;
import ltdd.doan.mangxahoi.data.model.Comment;
import ltdd.doan.mangxahoi.data.model.Conversation;
import ltdd.doan.mangxahoi.data.model.Message;
import ltdd.doan.mangxahoi.data.model.Post;
import ltdd.doan.mangxahoi.data.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    // Authenticaiton--------------------------------------------------------------------------------------------------------

    @POST("api/auth/register")
    Call<SuccessfullResponse<User>> register(@Body RegisterRequest request);
    @POST("api/auth/login")
    Call<SuccessfullResponse<User>> login(@Body LoginRequest request);

    //user --------------------------------------------------------------------------------------------------------
    @GET("api/search/user")
    Call<List<User>> filterUser(@Query("username") String username);
    @PUT("api/users/{id}")
    Call<SuccessfullResponse<User>> updateUser(@Path("id") String id);

    @GET("api/users/{id}")
    Call<SuccessfullResponse<User>> getUser(@Path("id") String id);
    @DELETE("api/users/{id}")
    Call<String> deleteUser(@Path("id") String id);
    @PUT("api/users/{id}/follow")
    Call<List<String>> followUser(@Path("id") String id, @Body FollowUserRequest request);
    @POST("api/users/{id}/checkIsFollowUser")
    Call<String> isFollowUser(@Path("id") String id, @Body FollowUserRequest request);

    // post--------------------------------------------------------------------------------------------------------
    @POST("api/posts")
    Call<SuccessfullResponse<Post>> createPost(@Body Post request);
    @GET("api/posts/{id}")
    Call<SuccessfullResponse<Post>> getPost(@Path("id") String id);
    @PUT("api/posts/{id}")
    Call<SuccessfullResponse<Post>> updatePost( @Body Post request,@Path("id") String id);
    @DELETE("api/posts/{id}")
    Call<String> deletePost(  @Path("id") String id);
    @PUT("api/posts/{id}/like")
    Call<LikePostResponse> likePost(@Body LikePostRequest request, @Path("id") String id);
    @GET("api/posts/{id}/save")
    Call<LikePostResponse> savePost(@Body Post request, @Path("id") String id);
    @GET("api/posts/all/{userId}")
    Call<SuccessfullResponse<List<Post>>> getPostsByUserId(@Path("userId") String userId);
    @GET("api/posts/timeline/{userId}")
    Call<ListFeedResponse> getPostTimelineByUser(@Path("userId") String userId , @Query("page") Integer page, @Query("limit") Integer limit );

    // Message
    @GET("api/conversations/{id}")
    Call<List<Conversation>> getAllConversation(@Path("id") String userId );

    @POST("api/conversations/")
    Call<Conversation> getOrCreateConversation(@Body CreateConversationRequest request);


    @GET("api/messages/{id}/")
    Call<ListMessageResponse> getMessagesFromConversation(@Path("id") String id );

    @POST("api/messages")
    Call<Message> sendMessage(@Body SendMessageRequest sendMessageRequest, @Query("page") Integer page, @Query("limit") Integer limit );





    @POST("api/comments")
    Call<SuccessfullResponse<Comment>> createComment(@Body Comment request);
    @GET("api/comments")
    Call<SuccessfullResponse<List<Comment>>> getComment(@Query("postId") String postId );
    @DELETE("api/comments/{id}")
    Call<String> deleteComment(@Path("id") String id);

    // Comment


}
