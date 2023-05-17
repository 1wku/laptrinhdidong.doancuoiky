package ltdd.doan.mangxahoi.ui.view.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Objects;

import io.socket.emitter.Emitter;
import ltdd.doan.mangxahoi.R;
import ltdd.doan.mangxahoi.data.model.Conversation;
import ltdd.doan.mangxahoi.data.model.Message;
import ltdd.doan.mangxahoi.data.model.User;
import ltdd.doan.mangxahoi.databinding.CardConversationBinding;
import ltdd.doan.mangxahoi.databinding.CardMessageBinding;
import ltdd.doan.mangxahoi.session.Session;
import ltdd.doan.mangxahoi.ui.view.fragment.FeedFragmentDirections;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {
    private Context context;
    private List<Message> messages;
    private MutableLiveData<Message> message ;

    public MutableLiveData<Message> getMessage() {
        return message;
    }

    public void setMessage(MutableLiveData<Message> message) {
        this.message = message;
    }

    private User partner;
    private LinearLayoutManager layoutManager;

    public ChatAdapter( Context context, List<Message> messages,User partner) {
        this.context = context;
        this.messages = messages;
        this.partner = partner;
        layoutManager = new LinearLayoutManager(context);
    }
    public void addMessage(Message message) {
        messages.add(message);
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardMessageBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.card_message,parent,false);
        return new ChatAdapter.ChatViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        Message message = messages.get(position);
        holder.binding.setChatAdapter(this);
        holder.binding.setMessage(message);

        if (!Objects.equals(message.getSendBy(), Session.getSharedPreference(context,"user_id",""))){
            holder.binding.frgFloatingRight.setVisibility(View.GONE);
            if (!Objects.equals(partner.getAvatar() , "")){
                Glide.with(context)
                        .load(partner.getAvatar())
                        .into(holder.binding.frgCardUserImgUserPhoto);
            }
        }
        else{
            holder.binding.frgCardUserImgUserPhoto.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }


    public class ChatViewHolder extends RecyclerView.ViewHolder {
        public CardMessageBinding binding;

        public ChatViewHolder(@NonNull CardMessageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }







}
