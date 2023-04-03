package ltdd.doan.mangxahoi.ui.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ltdd.doan.mangxahoi.R;
import ltdd.doan.mangxahoi.data.model.Post;
import ltdd.doan.mangxahoi.databinding.CardPostBinding;
import ltdd.doan.mangxahoi.ui.view.activity.MainActivity;
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

    public PostAdapterFeed(Context context, MainActivity mainActivity, FeedViewModel viewModel) {
        this.context = context;
        this.mainActivity = mainActivity;
        this.viewModel = viewModel;
    }

    public void setData(List<Post> data){
        this.posts = data;
    }



    //TODO
    public void navToPostOwnersProfile(View view, int user_id){
    }

    //TODO
    public void navToPostDetails(View view, int post_id){

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
        if (post == null){
            return;
        }

        holder.binding.setPostAdapterFeed(this);
        holder.binding.setPost(post);

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

}
