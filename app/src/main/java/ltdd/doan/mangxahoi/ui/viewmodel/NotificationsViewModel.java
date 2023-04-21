package ltdd.doan.mangxahoi.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import ltdd.doan.mangxahoi.data.model.Notification;
import ltdd.doan.mangxahoi.data.repository.NotificationRepository;

@HiltViewModel
public class NotificationsViewModel extends ViewModel {
    private NotificationRepository nRepo;
    private MutableLiveData<List<Notification>> notifications;

    @Inject
    public NotificationsViewModel(NotificationRepository nRepo) {
        this.nRepo = nRepo;

        notifications = nRepo.getNotifications();
    }

    public MutableLiveData<List<Notification>> getNotifications() {
        return notifications;
    }

    public void getAllNotifications() {
        nRepo.getAllNotifications();
    }

    public void markSeenNotifications(int noti_it){
        nRepo.markSeenNotifications(noti_it);
    }

    public void markSeenAllNotifications() {
        nRepo.markSeenAllNotifications();
    }

    public void deleteAllNotifications() {
        nRepo.deleteAllNotifications();
    }
}