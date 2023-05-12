package ltdd.doan.mangxahoi.ui.view.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import ltdd.doan.mangxahoi.R;
import ltdd.doan.mangxahoi.data.dto.response.LikePostResponse;
import ltdd.doan.mangxahoi.data.model.Post;
import ltdd.doan.mangxahoi.data.model.User;
import ltdd.doan.mangxahoi.data.repository.UserRepository;
import ltdd.doan.mangxahoi.databinding.CardPostBinding;
import ltdd.doan.mangxahoi.interfaces.OnGetPostResult;
import ltdd.doan.mangxahoi.interfaces.OnLikePostResult;
import ltdd.doan.mangxahoi.session.Session;
import ltdd.doan.mangxahoi.ui.view.activity.MainActivity;
import ltdd.doan.mangxahoi.ui.view.fragment.FeedFragmentDirections;
import ltdd.doan.mangxahoi.ui.viewmodel.FeedViewModel;

public class PostAdapterFeed extends RecyclerView.Adapter<PostAdapterFeed.PostViewHolder> {

    public class PostViewHolder extends RecyclerView.ViewHolder{
        public CardPostBinding binding;

        public PostViewHolder(@NonNull CardPostBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }
    private Context context;
    private MainActivity mainActivity;
    private List<Post> posts;
    private FeedViewModel viewModel;

    public PostAdapterFeed(Context context, MainActivity mainActivity, List<Post> posts, FeedViewModel viewModel) {
        this.context = context;
        this.mainActivity = mainActivity;
        this.posts = posts;
        this.viewModel = viewModel;
    }

    public void navToPostOwnersProfile(View view, String user_id){
        Bundle bundle = new Bundle();
        bundle.putString("user_id",user_id);
        Navigation.findNavController(view).navigate(FeedFragmentDirections.feedToProfile().getActionId(), bundle);
    }

    public void navToPostDetails(View view, String post_id){
        Bundle bundle = new Bundle();
        bundle.putString("post_id",post_id);
        Navigation.findNavController(view).navigate(FeedFragmentDirections.feedToPostDetails().getActionId(), bundle);
    }

    public void toggleLikePost(String post_id) {
        viewModel.like(post_id, new OnLikePostResult() {
            @Override
            public void onSuccess(LikePostResponse result) {
                System.out.println(result.likes);
                viewModel.getFeed();
            }

            @Override
            public void onError(String error) {
                System.out.println(error);

            }
        });
        // update ui
    }

    public boolean isPostLiked(Post post) {

        if (post.getLikers() == null) return false;

        for (String u : post.getLikers()) {
            if (u.equals(Session.getSharedPreference(context,"user_id",""))) {
                return true;
            }
        }
        return false;
    }



    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardPostBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.card_post,parent,false);
        return new PostViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = posts.get(position);


        holder.binding.setPostAdapterFeed(this);
        holder.binding.setPost(post);
        if (!Objects.equals(post.getPhoto() , "none image")){
            Glide.with(context)
                    .load(post.getPhoto())
                    .into(holder.binding.cardPostPostImg);
        }

        if (!Objects.equals(post.getOwnerData().getAvatar() , "")){
            Glide.with(context)
                    .load(post.getOwnerData().getAvatar())
                    .into(holder.binding.cardPostUserImg);
        }

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

}
