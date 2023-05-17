package ltdd.doan.mangxahoi.ui.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ltdd.doan.mangxahoi.R;
import ltdd.doan.mangxahoi.data.model.Post;
import ltdd.doan.mangxahoi.data.model.User;
import ltdd.doan.mangxahoi.databinding.CardPostThumbnailBinding;

public class SettingAdapter extends  RecyclerView.Adapter<SettingAdapter.SettingViewHolder>{
    @NonNull
    @Override
    public SettingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardPostThumbnailBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.card_post_thumbnail, parent, false);
        return new SettingAdapter.SettingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SettingViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class SettingViewHolder extends RecyclerView.ViewHolder{
        public CardPostThumbnailBinding binding;

        public SettingViewHolder(@NonNull CardPostThumbnailBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }
    private Context context;
    private List<Post> posts;

    public SettingAdapter(Context context, User user) {
        this.context = context;
        this.posts = posts;
    }

}
