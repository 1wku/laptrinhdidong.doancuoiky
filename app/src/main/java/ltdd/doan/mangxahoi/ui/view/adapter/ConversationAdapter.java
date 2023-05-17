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
import ltdd.doan.mangxahoi.data.model.Conversation;
import ltdd.doan.mangxahoi.data.model.Post;
import ltdd.doan.mangxahoi.data.model.User;
import ltdd.doan.mangxahoi.databinding.CardConversationBinding;
import ltdd.doan.mangxahoi.databinding.CardMessageBinding;
import ltdd.doan.mangxahoi.databinding.CardPostBinding;
import ltdd.doan.mangxahoi.session.Session;

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
        CardConversationBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.card_conversation,parent,false);
        return new ConversationAdapter.ConversationViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ConversationViewHolder holder, int position) {
        Conversation conversation = conversations.get(position);

        conversation.getMembers().forEach(user ->{
            if (!Objects.equals(user.getId(), Session.getSharedPreference(context,"user_id",""))){
                conversation.setPartner(user);
                if (!Objects.equals(user.getAvatar() , "")){
                    Glide.with(context)
                            .load(user.getAvatar())
                            .into(holder.binding.cardUserImgUserPhoto);
                }

            }
        });


        holder.binding.setConversationAdapter(this);
        holder.binding.setConversation(conversation);
    }

    @Override
    public int getItemCount() {
        return conversations.size();
    }

    public class ConversationViewHolder extends RecyclerView.ViewHolder {
        public CardConversationBinding binding;

        public ConversationViewHolder(@NonNull CardConversationBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


    public void navToChat(View view, String conversation_id, User partner) {
        Bundle bundle = new Bundle();
        bundle.putString("conversation_id",conversation_id);
        bundle.putString("partner_id",partner.getId());
        bundle.putString("partner_avatar",partner.getAvatar());
        bundle.putString("partner_email",partner.getEmail());
        Navigation.findNavController(view).navigate(ltdd.doan.mangxahoi.ui.view.fragment.ConversationFragmentDirections.conversationToChat().getActionId(), bundle);
    }
}
