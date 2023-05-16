package ltdd.doan.mangxahoi.ui.view.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import ltdd.doan.mangxahoi.R;
import ltdd.doan.mangxahoi.api.ApiUtils;
import ltdd.doan.mangxahoi.data.dto.request.MessageRequest;
import ltdd.doan.mangxahoi.data.model.Message;
import ltdd.doan.mangxahoi.data.model.User;
import ltdd.doan.mangxahoi.databinding.FragmentChatBinding;
import ltdd.doan.mangxahoi.databinding.FragmentConversationBinding;
import ltdd.doan.mangxahoi.databinding.FragmentFeedBinding;
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
    private String partnerId ="" ;
    private String partnerAvatar ="" ;
    private String conversationId ="" ;


    private Socket mSocket;
    {
        try {
            mSocket = IO.socket(ApiUtils.BASE_URL);
        } catch (URISyntaxException e) {}
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSocket.connect();
        mSocket.on("sendMessage", onNewMessage);
        mSocket.on("getMessage",onGetMessage);
        mViewModel = new ViewModelProvider(this).get(ChatViewModel.class);
    }

    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // add the message to view

                   mViewModel.getChatConversation(conversationId);
                }
            });
        }
    };


    private Emitter.Listener onGetMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // add the message to view
                    System.out.println(args);

                    mViewModel.getChatConversation(conversationId);
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
        String partnerAvatar = bundle.getString("partner_avatar") ;
        this.conversationId = conversationId;
        this.partnerId = partnerId;
        this.partnerAvatar = partnerAvatar;

        binding.frgChatRecyclerViewSwipeRefresh.setOnRefreshListener(() -> {
            mViewModel.getChatConversation(conversationId);
            binding.frgChatRecyclerViewSwipeRefresh.setRefreshing(false);
        });

        mViewModel.getMessages().observe(getViewLifecycleOwner(), messages -> {
            ChatAdapter chatAdapter = new ChatAdapter(requireContext(), messages, new User(partnerAvatar,partnerId));
            binding.setChatAdapter(chatAdapter);
        });


        mViewModel.getChatConversation(conversationId);


        return binding.getRoot();
    }

    public void sendMessage() {

        String message = binding.frgChatMessage.getText().toString();
        if (TextUtils.isEmpty(message)) {
            return;
        }
        binding.frgChatMessage.setText("");
        String userId =  Session.getSharedPreference(getContext(),"user_id","");

        mViewModel.sendMessage(conversationId, message, new OnSendMessageResult() {
            @Override
            public void onSuccess(Message result) throws JSONException {
                JSONObject obj = new JSONObject();
                obj.put("senderId", userId);
                obj.put("receiverId", partnerId);
                obj.put("message", message);
                mSocket.emit("sendMessage", obj);
                System.out.println(result.getText());
            }

            @Override
            public void onError(String error) {

            }
        });

    }
    @Override
    public void onDestroy() {
        super.onDestroy();

        mSocket.disconnect();
        mSocket.off("sendMessage", onNewMessage);
    }

}
