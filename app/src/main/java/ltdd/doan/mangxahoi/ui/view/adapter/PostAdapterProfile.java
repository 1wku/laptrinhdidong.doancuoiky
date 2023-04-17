package ltdd.doan.mangxahoi.ui.view.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ltdd.doan.mangxahoi.R;
import ltdd.doan.mangxahoi.data.model.Post;
import ltdd.doan.mangxahoi.databinding.CardPostThumbnailBinding;
import ltdd.doan.mangxahoi.ui.view.fragment.ProfileFragmentDirections;
import ltdd.doan.mangxahoi.ui.viewmodel.PostDetailsViewModel;
import ltdd.doan.mangxahoi.ui.viewmodel.ProfileViewModel;

//TODO
public class PostAdapterProfile extends RecyclerView.Adapter<PostAdapterProfile.PostThumbnailViewHolder>{
    public class PostThumbnailViewHolder extends RecyclerView.ViewHolder{
        public CardPostThumbnailBinding binding;

        public PostThumbnailViewHolder(@NonNull CardPostThumbnailBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }
    private Context context;
    private List<Post> posts;

    public PostAdapterProfile(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    public void navToPostDetails(View view, int post_id) {
        Bundle bundle = new Bundle();
        bundle.putInt("post_id",post_id);
        Navigation.findNavController(view).navigate(ProfileFragmentDirections.profileToPostDetails().getActionId(), bundle);
    }

    @NonNull
    @Override
    public PostThumbnailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardPostThumbnailBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.card_post_thumbnail, parent, false);
        return new PostThumbnailViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostThumbnailViewHolder holder, int position) {
        Post post = posts.get(position);

        holder.binding.setPostAdapterProfile(this);
        holder.binding.setPost(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }


}
