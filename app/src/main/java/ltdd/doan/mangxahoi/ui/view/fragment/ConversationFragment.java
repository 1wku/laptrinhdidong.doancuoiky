package ltdd.doan.mangxahoi.ui.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ltdd.doan.mangxahoi.R;
import ltdd.doan.mangxahoi.databinding.FragmentConversationBinding;
import ltdd.doan.mangxahoi.databinding.FragmentFriendBinding;
import ltdd.doan.mangxahoi.ui.view.adapter.ConversationAdapter;
import ltdd.doan.mangxahoi.ui.view.adapter.UserAdapterSearch;
import ltdd.doan.mangxahoi.ui.viewmodel.ConversationViewModel;
import ltdd.doan.mangxahoi.ui.viewmodel.FeedViewModel;
import ltdd.doan.mangxahoi.ui.viewmodel.SearchViewModel;

public class ConversationFragment extends Fragment {

    private FragmentConversationBinding binding;
    private ConversationViewModel mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ConversationViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_conversation, container, false);
        binding.setConversationFragment(this);
        mViewModel.getChat();

        mViewModel.getConversations().observe(getViewLifecycleOwner(), conversations -> {
            ConversationAdapter conversationAdapter = new ConversationAdapter(requireContext(), conversations);
            binding.setConversationAdapter(conversationAdapter);
        });


        // TODO: 4/21/2023 chat

        return binding.getRoot();
    }

}
