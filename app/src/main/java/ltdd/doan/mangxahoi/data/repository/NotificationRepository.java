package ltdd.doan.mangxahoi.data.repository;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import ltdd.doan.mangxahoi.api.ApiInterface;
import ltdd.doan.mangxahoi.data.model.Notification;

public class NotificationRepository {
    private final ApiInterface apiService;
    private MutableLiveData<List<Notification>> notifications;
    private MutableLiveData<Integer> unseenNotificationCount;
    private MutableLiveData<String> message; // messages from response
    private MutableLiveData<Boolean> status; // status for navigation

    public NotificationRepository(ApiInterface apiService) {
        this.apiService = apiService;

        notifications = new MutableLiveData<>();
        unseenNotificationCount = new MutableLiveData<>();
        message = new MutableLiveData<>();
        status = new MutableLiveData<>();
    }

    public MutableLiveData<List<Notification>> getNotifications() {
        return notifications;
    }

    public MutableLiveData<Integer> getUnseenNotificationCount() {
        return unseenNotificationCount;
    }

    // TODO: 4/18/2023
    public void getAllNotifications(){

    }

    // TODO: 4/18/2023
    public void markSeenNotifications(int noti_id){

    }

    // TODO: 4/18/2023
    public void markSeenAllNotifications(){

    }

    // TODO: 4/18/2023
    public void deleteAllNotifications(){

    }
}
