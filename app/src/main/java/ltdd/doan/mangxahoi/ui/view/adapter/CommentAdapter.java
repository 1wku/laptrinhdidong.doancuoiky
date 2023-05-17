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
import ltdd.doan.mangxahoi.data.model.Comment;
import ltdd.doan.mangxahoi.data.model.Conversation;
import ltdd.doan.mangxahoi.data.model.Post;
import ltdd.doan.mangxahoi.databinding.CardCommentBinding;
import ltdd.doan.mangxahoi.databinding.CardConversationBinding;
import ltdd.doan.mangxahoi.databinding.CardPostThumbnailBinding;
import ltdd.doan.mangxahoi.ui.view.fragment.ProfileFragmentDirections;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder>{
    private Context context;
    private List<Comment> comments;

    public CommentAdapter(Context context, List<Comment> comments) {
        this.context = context;
        this.comments = comments;
    }
    public void navToUserProfile(View view, String user_id) {
        Bundle bundle = new Bundle();
        bundle.putString("user_id",user_id);
        Navigation.findNavController(view).navigate(ltdd.doan.mangxahoi.ui.view.fragment.PostDetailsFragmentDirections.followToProfile().getActionId(), bundle);
    }
    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardCommentBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.card_comment, parent, false);
        return new CommentAdapter.CommentViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        Comment comment = comments.get(position);
        holder.binding.setCommentAdapter(this);
        holder.binding.setComment(comment);

        if (!Objects.equals(comment.getOwnerData().getAvatar() , "")){
            Glide.with(context)
                    .load(comment.getOwnerData().getAvatar())
                    .into(holder.binding.cardCommentImgUserPhoto);
        }

    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {
        public CardCommentBinding binding;

        public CommentViewHolder(@NonNull CardCommentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
