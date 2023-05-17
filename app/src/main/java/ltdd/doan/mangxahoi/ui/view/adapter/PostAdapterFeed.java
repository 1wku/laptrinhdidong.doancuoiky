package ltdd.doan.mangxahoi.ui.view.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
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
import ltdd.doan.mangxahoi.interfaces.OnDeletePostResult;
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

    private MutableLiveData<Boolean> isLiked;

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

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

    @Override
    public int getItemViewType(int position) {
        return posts.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
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



    public boolean isPostLiked(Post post) {

        if (post.getLikers() == null) return false;

        for (String u : post.getLikers()) {
            if (u != null ){
                if (u.equals(Session.getSharedPreference(context,"user_id",""))) {
                    return true;
                }
            }

        }
        return false;
    }

    public Boolean isLike(LikePostResponse res){
        for (String u : res.likes) {
            if (u != null) {
                if (u.equals(Session.getSharedPreference(context, "user_id", ""))) {
                    return true;
                }
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

        holder.binding.cardPostPopupMenu.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PopupMenu popupMenu = new PopupMenu(context, holder.binding.cardPostPopupMenu);

                        // Inflating popup menu from popup_menu.xml file
                        popupMenu.getMenuInflater().inflate(R.menu.menu_popup_post, popupMenu.getMenu());
                        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem menuItem) {

                                // TODO : delete + edit post

                                CharSequence title = menuItem.getTitle();
                                if ("Edit Post".equals(title)) {
                                    Toast.makeText(context, "You Clicked edit " + menuItem.getItemId(), Toast.LENGTH_SHORT).show();

                                } else if ("Delete Post".equals(title)) {
                                    if (post.getOwner().equals(Session.getSharedPreference(context,"user_id",""))){
                                        viewModel.deletePost(post.getId(), new OnDeletePostResult() {
                                            @Override
                                            public void onSuccess(String result) {
                                                Toast.makeText(context, "Xoá bài đăng thành công  ", Toast.LENGTH_SHORT).show();
                                                navToPostDetails(holder.binding.cardPostPostImg,Session.getSharedPreference(context,"user_id",""));
                                            }

                                            @Override
                                            public void onError(String error) {
                                                Toast.makeText(context, error, Toast.LENGTH_SHORT).show();

                                            }
                                        });
                                    }
                                    else Toast.makeText(context, "Bạn không có quyền xoá bài đăng này", Toast.LENGTH_SHORT).show();
                                }
                                return true;
                            }
                        });
                        popupMenu.show();
                    }
                }
        );


        if (!Objects.equals(post.getOwnerData().getAvatar() , "")){
            Glide.with(context)
                    .load(post.getOwnerData().getAvatar())
                    .into(holder.binding.cardPostUserImg);
        }
        holder.binding.cardPostPostlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.like(post.getId(), new OnLikePostResult() {
                    @Override
                    public void onSuccess(LikePostResponse result) {
                        if ( isLike(result)){
                            holder.binding.cardPostPostlike.setImageResource(R.drawable.ic_heart_red);
                        }else{
                            holder.binding.cardPostPostlike.setImageResource(R.drawable.ic_heart);
                        }
                        holder.binding.cardPostPostlikeAmount.setText(result.likes.size()+ " lượt thích");
                    }

                    @Override
                    public void onError(String error) {

                    }
                });

            }
        });


    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

}
