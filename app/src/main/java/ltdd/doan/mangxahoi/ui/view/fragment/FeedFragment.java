package ltdd.doan.mangxahoi.ui.view.fragment;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

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
    boolean isLoading = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FeedViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_feed,container,false);
        mViewModel.setPage(1);
        mViewModel.getFeed();



        binding.frgFeedRecyclerViewSwipeRefresh.setOnRefreshListener(() -> {
                  mViewModel.setPage(1);
            mViewModel.getFeed();
            binding.frgFeedRecyclerViewSwipeRefresh.setRefreshing(false);
        });

        binding.frgFeedRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!isLoading) {
                    if (mViewModel.getPosts().getValue()!= null){
                        if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() >= mViewModel.getPosts().getValue().size()-2) {
                            //bottom of list!
                            isLoading = true;
                            mViewModel.getFeed();
                            isLoading = false ;
                        }
                    }

                }
            }
        });


        mViewModel.getPosts().observe(getViewLifecycleOwner(), posts -> {
            PostAdapterFeed postAdapter = new PostAdapterFeed(requireContext(), (MainActivity) requireActivity(), posts, mViewModel);
            binding.setPostAdapter(postAdapter);

            if (posts.size() == 0) binding.frgFeedLblMsgNoPost.setVisibility(View.VISIBLE);
            else binding.frgFeedLblMsgNoPost.setVisibility(View.GONE);

        });

        mViewModel.getMessage().observe(getViewLifecycleOwner(),message ->{
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        });

        return binding.getRoot();
    }






}