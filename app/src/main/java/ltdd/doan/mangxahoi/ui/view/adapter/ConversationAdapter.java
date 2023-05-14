package ltdd.doan.mangxahoi.ui.view.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ltdd.doan.mangxahoi.data.model.Conversation;
import ltdd.doan.mangxahoi.data.model.User;
import ltdd.doan.mangxahoi.databinding.CardConversationBinding;
import ltdd.doan.mangxahoi.databinding.CardMessageBinding;

public class ConversationAdapter  extends RecyclerView.Adapter<ConversationAdapter.ConversationViewHolder>{
    private Context context;
    private List<Conversation> conversations;

    public ConversationAdapter(Context context, List<Conversation> conversations) {
        this.context = context;
        this.conversations = conversations;
    }

    @NonNull
    @Override
    public ConversationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ConversationViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ConversationViewHolder extends RecyclerView.ViewHolder {
        public CardConversationBinding binding;

        public ConversationViewHolder(@NonNull CardConversationBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void navToChat(View view, String user_id) {
        Bundle bundle = new Bundle();
        bundle.putString("user_id",user_id);
        Navigation.findNavController(view).navigate(ltdd.doan.mangxahoi.ui.view.fragment.FollowFragmentDirections.followToProfile().getActionId(), bundle);
    }
}
