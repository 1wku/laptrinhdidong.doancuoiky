package ltdd.doan.mangxahoi.ui.view.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import ltdd.doan.mangxahoi.R;
import ltdd.doan.mangxahoi.api.ApiUtils;
import ltdd.doan.mangxahoi.data.dto.request.MessageRequest;
import ltdd.doan.mangxahoi.data.dto.response.ListMessageResponse;
import ltdd.doan.mangxahoi.data.model.Message;
import ltdd.doan.mangxahoi.data.model.User;
import ltdd.doan.mangxahoi.databinding.FragmentChatBinding;
import ltdd.doan.mangxahoi.databinding.FragmentConversationBinding;
import ltdd.doan.mangxahoi.databinding.FragmentFeedBinding;
import ltdd.doan.mangxahoi.interfaces.OnGetMessageResult;
import ltdd.doan.mangxahoi.interfaces.OnSendMessageResult;
import ltdd.doan.mangxahoi.session.Session;
import ltdd.doan.mangxahoi.ui.view.adapter.ChatAdapter;
import ltdd.doan.mangxahoi.ui.view.adapter.ConversationAdapter;
import ltdd.doan.mangxahoi.ui.viewmodel.ChatViewModel;
import ltdd.doan.mangxahoi.ui.viewmodel.ConversationViewModel;
import ltdd.doan.mangxahoi.ui.viewmodel.FeedViewModel;

@AndroidEntryPoint
public class ChatFragment extends Fragment {
    private FragmentChatBinding binding;
    private ChatViewModel mViewModel;

    private MutableLiveData<List<Message>> messages;
    private MutableLiveData<Message> newMessage;
    private String partnerId ="" ;
    private String partnerAvatar ="" ;
    private String userId="";
    private String conversationId ="" ;

    public ChatFragment(){
        newMessage = new MutableLiveData<>();
        messages = new MutableLiveData<>();
    }
    private Socket mSocket;
    {
        try {
            mSocket = IO.socket(ApiUtils.BASE_URL);
        } catch (URISyntaxException e) {}
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        userId = Session.getSharedPreference(getContext(),"user_id","");
        super.onCreate(savedInstanceState);
        mSocket.on("getMessage", onNewMessage);
        mSocket.connect();

        mViewModel = new ViewModelProvider(this).get(ChatViewModel.class);
    }

    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // add the message to view
                    JSONObject data = (JSONObject) args[0];
                    String receiverId;
                    String senderId;
                    String message;
                    try {
                        receiverId = data.getString("receiverId");
                        senderId = data.getString("senderId");
                        message = data.getString("message");
                        if (Objects.equals(Session.getSharedPreference(getContext(),"user_id",""),receiverId)){
                            Message a = new Message(conversationId,senderId,message) ;
                            newMessage.setValue(a);
                        }
                    } catch (JSONException e) {
                        return;
                    }
                }
            });
        }
    };



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat, container, false);
        binding.setChatFragment(this);

        Bundle bundle = getArguments();
        String conversationId = bundle.getString("conversation_id") ;
        String partnerId = bundle.getString("partner_id") ;
        this.conversationId = conversationId;
        this.partnerId = partnerId;

        binding.btnSendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String message = binding.frgChatMessage.getText().toString();
                if (TextUtils.isEmpty(message)) {
                    return;
                }
                binding.frgChatMessage.setText("");


                mViewModel.sendMessage(conversationId, message, new OnSendMessageResult() {
                    @Override
                    public void onSuccess(Message result) throws JSONException {
                        JSONObject obj = new JSONObject();
                        obj.put("senderId", userId);
                        obj.put("receiverId", partnerId);
                        obj.put("message", message);
                        mSocket.emit("sendMessage", obj);
                        newMessage.setValue(new Message(conversationId,userId,message));
                    }

                    @Override
                    public void onError(String error) {

                    }
                });
            }
        });

        binding.frgChatRecyclerViewSwipeRefresh.setOnRefreshListener(() -> {
            mViewModel.getChatConversation(conversationId, new OnGetMessageResult() {
                @Override
                public void onSuccess(ListMessageResponse result) {
                    messages.setValue(result.messages);
                }

                @Override
                public void onError(String error) {
                    System.out.println(error);

                }
            });
            binding.frgChatRecyclerViewSwipeRefresh.setRefreshing(false);
        });
        newMessage.observe(getViewLifecycleOwner(),message -> {
            binding.getChatAdapter().addMessage(message);
            binding.getChatAdapter().notifyDataSetChanged();
            binding.frgSearchRecyclerView.smoothScrollToPosition(  binding.getChatAdapter().getItemCount());

        });

        messages.observe(getViewLifecycleOwner(), messages -> {
            if (messages == null){
                ChatAdapter chatAdapter = new ChatAdapter(requireContext(), new ArrayList<>(), new User(partnerAvatar,partnerId));
                binding.setChatAdapter(chatAdapter);
            }
            else{
                ChatAdapter chatAdapter = new ChatAdapter(requireContext(), messages, new User(partnerAvatar,partnerId));
                binding.frgSearchRecyclerView.scrollToPosition(messages.size());
                binding.setChatAdapter(chatAdapter);
            }

        });


        mViewModel.getChatConversation(conversationId,new OnGetMessageResult() {
            @Override
            public void onSuccess(ListMessageResponse result) {
                messages.setValue(result.messages);
            }

            @Override
            public void onError(String error) {
                System.out.println(error);

            }
        });


        return binding.getRoot();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        mSocket.disconnect();
        mSocket.off("sendMessage", onNewMessage);
    }

}
