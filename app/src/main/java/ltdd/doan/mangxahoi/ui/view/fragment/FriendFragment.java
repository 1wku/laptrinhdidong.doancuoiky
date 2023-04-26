package ltdd.doan.mangxahoi.ui.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dagger.hilt.android.AndroidEntryPoint;
import ltdd.doan.mangxahoi.R;
import ltdd.doan.mangxahoi.databinding.FragmentFriendBinding;
import ltdd.doan.mangxahoi.ui.view.adapter.UserAdapterSearch;
import ltdd.doan.mangxahoi.ui.viewmodel.SearchViewModel;
@AndroidEntryPoint
public class FriendFragment extends Fragment {

    private FragmentFriendBinding binding;
    private SearchViewModel mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_friend, container, false);
        binding.setFriendFragment(this);
        binding.setFriendFragment(this);

        mViewModel.getUsers().observe(getViewLifecycleOwner(), users -> {
            UserAdapterSearch userAdapter = new UserAdapterSearch(requireContext(), users);
            binding.setUserAdapter(userAdapter);
        });

        mViewModel.filterUsersByName(binding.frgSearchTxtUserName.getEditableText().toString());

        // TODO: 4/21/2023 chat

        return binding.getRoot();
    }

    public void onSearchTextChanged(String text) {
        mViewModel.filterUsersByName(text);
    }
}