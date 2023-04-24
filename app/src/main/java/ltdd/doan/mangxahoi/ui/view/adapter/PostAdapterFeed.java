package ltdd.doan.mangxahoi.ui.view.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Iterator;
import java.util.List;

import ltdd.doan.mangxahoi.R;
import ltdd.doan.mangxahoi.data.model.Post;
import ltdd.doan.mangxahoi.databinding.CardPostBinding;
import ltdd.doan.mangxahoi.interfaces.OnGetPostResult;
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

    public void likePost(String post_id) {
        viewModel.like(post_id);

        // update ui
        viewModel.getFeed();
    }

    public void unlikePost(String post_id) {
        viewModel.unlike(post_id);

        // update ui
        viewModel.getFeed();

    }
    public boolean isPostLiked(Post post) {

        if (post.getLikers() == null) return false;

        for (String u : post.getLikers()) {
            if (u.equals(Session.ACTIVE_USER.getId())) {
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

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

}
