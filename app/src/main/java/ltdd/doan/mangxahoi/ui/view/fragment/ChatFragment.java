package ltdd.doan.mangxahoi.ui.view.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.List;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import ltdd.doan.mangxahoi.api.ApiUtils;
import ltdd.doan.mangxahoi.databinding.FragmentFeedBinding;
import ltdd.doan.mangxahoi.ui.view.adapter.ChatAdapter;
import ltdd.doan.mangxahoi.ui.viewmodel.FeedViewModel;

public class ChatFragment extends Fragment {
    private RecyclerView mRecyclerViewChat;
    private Button mButtonLogin; private Button muttonChat;
    private EditText mEditTextName;
    private List<String> mListMessages;
    private ChatAdapter mChatAdapter;

    private FeedViewModel mViewModel;
    public FragmentFeedBinding binding;

    private final String URL_SERVER = ApiUtils.BASE_URL;
    private Socket mSocket;
    {
        try {
            mSocket = IO.socket(URL_SERVER);
        } catch (URISyntaxException e) {}
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSocket.on("new message", onNewMessage);
        mSocket.connect();
        mViewModel = new ViewModelProvider(this).get(FeedViewModel.class);
    }

    private void attemptSend() {
        String message = mEditTextName.getText().toString().trim();
        if (TextUtils.isEmpty(message)) {
            return;
        }

        mEditTextName.setText("");
//        mSocket.emit("sendMessage", { senderId, receiverId, message });

    }


    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject)args[0];
                    String username;
                    String message;
                    try {
                        username = data.getString("username");
                        message = data.getString("message");
                    } catch (JSONException e) {
                        return;
                    }

                    // add the message to view
//                    addMessage(username, message);
                }
            });
        }
    };
    @Override
    public void onDestroy() {
        super.onDestroy();

        mSocket.disconnect();
        mSocket.off("new message", onNewMessage);
    }
}
