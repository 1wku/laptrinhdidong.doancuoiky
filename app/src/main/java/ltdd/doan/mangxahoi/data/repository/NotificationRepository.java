package ltdd.doan.mangxahoi.data.repository;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import ltdd.doan.mangxahoi.api.ApiInterface;
import ltdd.doan.mangxahoi.data.model.Notification;
import ltdd.doan.mangxahoi.session.Session;

public class NotificationRepository {
    private final ApiInterface apiService;
    private MutableLiveData<List<Notification>> notifications;
    private MutableLiveData<String> message; // messages from response
    private MutableLiveData<Boolean> status; // status for navigation

    public NotificationRepository(ApiInterface apiService) {
        this.apiService = apiService;

        notifications = new MutableLiveData<>();
        message = new MutableLiveData<>();
        status = new MutableLiveData<>();
    }

    public MutableLiveData<List<Notification>> getNotifications() {
        return notifications;
    }


    // TODO: 4/18/2023
    public void getAllNotifications(){
        List<Notification> temp = new ArrayList<>();
        for (int i = 0; i<20;i++){
            temp.add(new Notification().gerEx(1));
        }
        notifications.setValue(temp);
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
