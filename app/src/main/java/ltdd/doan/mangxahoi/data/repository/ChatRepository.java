package ltdd.doan.mangxahoi.data.repository;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import ltdd.doan.mangxahoi.api.ApiInterface;
import ltdd.doan.mangxahoi.data.model.Message;
import ltdd.doan.mangxahoi.data.model.Notification;

public class ChatRepository {
    private final ApiInterface apiService;
    private final Context context;
    private MutableLiveData<List<Message>> messages;
    private MutableLiveData<String> message; // messages from response

    public ChatRepository(Context context, ApiInterface apiService) {
        this.apiService = apiService;
        this.context = context ;
        messages = new MutableLiveData<>();
        message = new MutableLiveData<>();
    }


}
