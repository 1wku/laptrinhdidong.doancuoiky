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

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Objects;

import ltdd.doan.mangxahoi.R;
import ltdd.doan.mangxahoi.data.model.Post;
import ltdd.doan.mangxahoi.databinding.CardPostThumbnailBinding;
import ltdd.doan.mangxahoi.ui.view.fragment.PostDetailsFragment;
import ltdd.doan.mangxahoi.ui.view.fragment.ProfileFragmentDirections;

//TODO
public class PostAdapterDetail extends RecyclerView.Adapter<PostAdapterDetail.PostDetailViewHolder>{
    public class PostDetailViewHolder extends RecyclerView.ViewHolder{
        public PostDetailsFragment binding;

        public PostDetailViewHolder(@NonNull CardPostThumbnailBinding binding){
            super(binding.getRoot());
//            this.binding = binding;
        }
    }
    private Context context;
    private List<Post> posts;

    public PostAdapterDetail(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    public void navToPostDetails(View view, String post_id) {
        Bundle bundle = new Bundle();
        bundle.putString("post_id",post_id);
        Navigation.findNavController(view).navigate(ProfileFragmentDirections.profileToPostDetails().getActionId(), bundle);
    }

    @NonNull
    @Override
    public PostDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardPostThumbnailBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.card_post_thumbnail, parent, false);
        return new PostDetailViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostDetailViewHolder holder, int position) {
//        Post post = posts.get(position);
//
//        holder.binding.setPostAdapterProfile(this);
//        holder.binding.setPost(post);
//        if (!Objects.equals(post.getPhoto() , "none image")){
//            Glide.with(context)
//                    .load(post.getPhoto())
//                    .into(holder.binding.cardPostThumbnailImgPostPhoto);
//        }
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }


}
