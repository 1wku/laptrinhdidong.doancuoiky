package ltdd.doan.mangxahoi.data.repository;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import org.json.JSONException;

import java.util.List;

import ltdd.doan.mangxahoi.api.ApiInterface;
import ltdd.doan.mangxahoi.data.dto.request.SendMessageRequest;
import ltdd.doan.mangxahoi.data.dto.response.ListMessageResponse;
import ltdd.doan.mangxahoi.data.model.Conversation;
import ltdd.doan.mangxahoi.data.model.Message;
import ltdd.doan.mangxahoi.data.model.Notification;
import ltdd.doan.mangxahoi.interfaces.OnGetMessageResult;
import ltdd.doan.mangxahoi.interfaces.OnSendMessageResult;
import ltdd.doan.mangxahoi.session.Session;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    public void getMessagesFromConversation(String id, OnGetMessageResult onGetMessageResult){
        apiService.getMessagesFromConversation(id).enqueue(new Callback<ListMessageResponse>() {
            @Override
            public void onResponse(Call<ListMessageResponse> call, Response<ListMessageResponse> response) {
                if(response.code()==200) {
                    System.out.println(response.body());
                    onGetMessageResult.onSuccess(response.body());
                }
                else onGetMessageResult.onError(response.message());
            }
            @Override
            public void onFailure(Call<ListMessageResponse> call, Throwable t) {
                System.out.println(t.getMessage());
                onGetMessageResult.onError(t.getMessage());
            }
        });
    }

    public void sendMessage( String conversationId,String message, OnSendMessageResult onSendMessageResult){
        String userId = Session.getSharedPreference(context,"user_id","");
        apiService.sendMessage(new SendMessageRequest(conversationId,userId,message)).enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                if(response.code()==200) {
                    System.out.println(response.body());
                    try {
                        onSendMessageResult.onSuccess(response.body());
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                else onSendMessageResult.onError(response.message());
            }
            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                System.out.println(t.getMessage());
                onSendMessageResult.onError(t.getMessage());
            }
        });
    }


}
