package ltdd.doan.mangxahoi.ui.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

import ltdd.doan.mangxahoi.R;
import ltdd.doan.mangxahoi.data.model.Notification;
import ltdd.doan.mangxahoi.databinding.CardNotificationBinding;
import ltdd.doan.mangxahoi.databinding.CardUserBinding;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>{

    public class NotificationViewHolder extends RecyclerView.ViewHolder {
        CardNotificationBinding binding;

        public NotificationViewHolder(@NonNull CardNotificationBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private Context context;
    private List<Notification> notifications;

    public NotificationAdapter(Context context, List<Notification> notifications) {
        this.context = context;
        this.notifications = notifications;
    }

    //todo
    public void navToResource(View view, Notification notification){

    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardNotificationBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.card_notification, parent, false);
        return new NotificationViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        Notification notification = notifications.get(position);

        holder.binding.setNotificationAdapter(this);
        holder.binding.setNotification(notification);

        //todo lấy tên user
        if(Objects.equals(notification.getType(), "1")){
            holder.binding.cardNotificationLblNotificationText.setText("Bạn có tin nhắn từ "+notification.getFrom());
        } else if (Objects.equals(notification.getType(), "2")) {
            holder.binding.cardNotificationLblNotificationText.setText("Bài dăng bạn có lượt thích mới");
        }else {
            holder.binding.cardNotificationLblNotificationText.setText("Bài đăng của bạn có bình luận mới");
        }
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

}
