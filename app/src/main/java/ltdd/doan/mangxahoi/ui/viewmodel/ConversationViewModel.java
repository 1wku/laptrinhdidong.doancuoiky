package ltdd.doan.mangxahoi.ui.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import ltdd.doan.mangxahoi.data.model.Conversation;
import ltdd.doan.mangxahoi.data.model.User;
import ltdd.doan.mangxahoi.data.repository.ChatRepository;
import ltdd.doan.mangxahoi.data.repository.ConversationRepository;
import ltdd.doan.mangxahoi.data.repository.UserRepository;
import ltdd.doan.mangxahoi.interfaces.OnFilterUserResult;
import ltdd.doan.mangxahoi.interfaces.OnGetConversationResult;

public class ConversationViewModel extends ViewModel {

    private ConversationRepository cRepo;
    private MutableLiveData<List<Conversation>> conversations;

    @Inject
    public ConversationViewModel(ConversationRepository cRepo) {
        this.cRepo = cRepo;
        conversations = new MutableLiveData<List<Conversation>>();
    }


    public MutableLiveData<List<Conversation>> getConversations() {
       return conversations;
    }

    public void getChat(){
        cRepo.getAllConversation(new OnGetConversationResult() {
            @Override
            public void onSuccess(List<Conversation> result) {
                conversations.setValue(result);
            }

            @Override
            public void onError(String error) {
                System.out.println(error);
            }
        });
    }
}
