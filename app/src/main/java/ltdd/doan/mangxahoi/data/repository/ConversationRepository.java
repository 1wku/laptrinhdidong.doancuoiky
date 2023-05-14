package ltdd.doan.mangxahoi.data.repository;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import ltdd.doan.mangxahoi.api.ApiInterface;
import ltdd.doan.mangxahoi.data.dto.response.SuccessfullResponse;
import ltdd.doan.mangxahoi.data.model.Conversation;
import ltdd.doan.mangxahoi.data.model.Message;
import ltdd.doan.mangxahoi.data.model.Post;
import ltdd.doan.mangxahoi.interfaces.OnGetConversationResult;
import ltdd.doan.mangxahoi.interfaces.OnGetPostsByUserResult;
import ltdd.doan.mangxahoi.session.Session;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConversationRepository {
    private final ApiInterface apiService;
    private final Context context;
    private MutableLiveData<List<Message>> messages;
    private MutableLiveData<String> message; // messages from response

    public ConversationRepository(Context context, ApiInterface apiService) {
        this.apiService = apiService;
        this.context = context ;
        messages = new MutableLiveData<>();
        message = new MutableLiveData<>();
    }

    public void getAllConversation(OnGetConversationResult onGetConversationResult){
        String userId = Session.getSharedPreference(context, "user_id", "");
        apiService.getAllConversation(userId).enqueue(new Callback<List<Conversation>>() {
            @Override
            public void onResponse(Call<List<Conversation>> call, Response<List<Conversation>> response) {
                if(response.code()==200) {
                    System.out.println(response.body());
                    onGetConversationResult.onSuccess(response.body());
                }
                else onGetConversationResult.onError(response.message());
            }
            @Override
            public void onFailure(Call<List<Conversation>> call, Throwable t) {
                System.out.println(t.getMessage());
                onGetConversationResult.onError(t.getMessage());
            }
        });

    }

}
