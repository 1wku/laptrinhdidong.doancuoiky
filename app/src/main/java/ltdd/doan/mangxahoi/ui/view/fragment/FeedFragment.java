package ltdd.doan.mangxahoi.ui.view.fragment;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dagger.hilt.android.AndroidEntryPoint;
import ltdd.doan.mangxahoi.R;
import ltdd.doan.mangxahoi.databinding.FragmentFeedBinding;
import ltdd.doan.mangxahoi.ui.view.activity.MainActivity;
import ltdd.doan.mangxahoi.ui.view.adapter.PostAdapterFeed;
import ltdd.doan.mangxahoi.ui.viewmodel.FeedViewModel;
@AndroidEntryPoint
public class FeedFragment extends Fragment {

    private FeedViewModel mViewModel;
    public FragmentFeedBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FeedViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_feed,container,false);

        mViewModel.getFeed();

        binding.frgFeedRecyclerViewSwipeRefresh.setOnRefreshListener(() -> {
            mViewModel.getFeed();
            binding.frgFeedRecyclerViewSwipeRefresh.setRefreshing(false);
        });

        mViewModel.getPosts().observe(getViewLifecycleOwner(), posts -> {
            PostAdapterFeed postAdapter = new PostAdapterFeed(requireContext(), (MainActivity) requireActivity(), posts, mViewModel);
            binding.setPostAdapter(postAdapter);

            if (posts.size() == 0) binding.frgFeedLblMsgNoPost.setVisibility(View.VISIBLE);
            else binding.frgFeedLblMsgNoPost.setVisibility(View.GONE);
        });

        return binding.getRoot();
    }



}