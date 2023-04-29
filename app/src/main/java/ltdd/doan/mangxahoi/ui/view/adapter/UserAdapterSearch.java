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
import ltdd.doan.mangxahoi.data.model.User;
import ltdd.doan.mangxahoi.databinding.CardUserBinding;

public class UserAdapterSearch extends RecyclerView.Adapter<UserAdapterSearch.UserViewHolder> {

    private Context context;
    private List<User> users;

    public UserAdapterSearch(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        public CardUserBinding binding;

        public UserViewHolder(@NonNull CardUserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void navToUserProfile(View view, String user_id){
        Bundle bundle = new Bundle();
        bundle.putString("user_id",user_id);
        Navigation.findNavController(view).navigate(ltdd.doan.mangxahoi.ui.view.fragment.FriendFragmentDirections.friendToProfile().getActionId(), bundle);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardUserBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.card_user, parent, false);
        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = users.get(position);

        holder.binding.setUserAdapterFriend(this);
        holder.binding.setUser(user);

        // TODO: 4/21/2023 ảnh
        if (!Objects.equals(user.getAvatar() , "none image") && !Objects.equals(user.getAvatar() , "")){
            Glide.with(context)
                    .load(user.getAvatar() )
                    .into(holder.binding.cardUserImgUserPhoto);
        }

    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
