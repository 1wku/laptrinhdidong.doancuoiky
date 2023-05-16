package ltdd.doan.mangxahoi.ui.viewmodel;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import ltdd.doan.mangxahoi.data.dto.response.ListMessageResponse;
import ltdd.doan.mangxahoi.data.model.Conversation;
import ltdd.doan.mangxahoi.data.model.Message;
import ltdd.doan.mangxahoi.data.model.User;
import ltdd.doan.mangxahoi.data.repository.ChatRepository;
import ltdd.doan.mangxahoi.data.repository.ConversationRepository;
import ltdd.doan.mangxahoi.data.repository.UserRepository;
import ltdd.doan.mangxahoi.interfaces.OnGetConversationResult;
import ltdd.doan.mangxahoi.interfaces.OnGetMessageResult;
import ltdd.doan.mangxahoi.interfaces.OnGetUserDetailResult;
import ltdd.doan.mangxahoi.interfaces.OnSendMessageResult;

@HiltViewModel
public class ChatViewModel extends ViewModel {
    private ChatRepository cRepo;
    private UserRepository uRepo;
    private MutableLiveData<List<Message>> messages;
    private MutableLiveData<User> partners;

    @Inject
    public ChatViewModel(ChatRepository cRepo, UserRepository uRepo) {
        this.cRepo = cRepo;
        this.uRepo = uRepo;
        messages = new MutableLiveData<List<Message>>();
    }
    public void setConversations(MutableLiveData<List<Message>> messages) {
        this.messages = messages;
    }

    public MutableLiveData<List<Message>> getMessages() {
        return messages;
    }

    public MutableLiveData<User> getPartners() {
        return partners;
    }



    public void getChatConversation(String id){
        cRepo.getMessagesFromConversation(id, new OnGetMessageResult() {
            @Override
            public void onSuccess(ListMessageResponse result) {
                messages.setValue(result.messages);
            }

            @Override
            public void onError(String error) {
                System.out.println(error);

            }
        });
    }

    public void sendMessage(String conversationId, String message , OnSendMessageResult onSendMessageResult){
        cRepo.sendMessage(conversationId, message, onSendMessageResult );
    }

}
