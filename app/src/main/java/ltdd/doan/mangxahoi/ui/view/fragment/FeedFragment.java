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

import java.util.ArrayList;
import java.util.List;

import ltdd.doan.mangxahoi.R;
import ltdd.doan.mangxahoi.data.model.Post;
import ltdd.doan.mangxahoi.data.model.User;
import ltdd.doan.mangxahoi.databinding.FragmentFeedBinding;
import ltdd.doan.mangxahoi.ui.view.activity.MainActivity;
import ltdd.doan.mangxahoi.ui.view.adapter.PostAdapterFeed;
import ltdd.doan.mangxahoi.ui.viewmodel.FeedViewModel;

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

        PostAdapterFeed postAdapter = new PostAdapterFeed(requireContext(), (MainActivity) requireActivity(), mViewModel);
        postAdapter.setData(getFeed());
        binding.setPostAdapter(postAdapter);

        binding.frgFeedRecyclerViewSwipeRefresh.setOnRefreshListener(() -> {
            postAdapter.setData(getFeed());
            binding.frgFeedRecyclerViewSwipeRefresh.setRefreshing(false);
        });

//        if (posts.size() == 0) binding.frgFeedMsgNoPost.setVisibility(View.VISIBLE);
//        else binding.frgFeedMsgNoPost.setVisibility(View.GONE);
        return binding.getRoot();
    }

    public List<Post> getFeed(){
        User user1 = new User(1,"user email1","phone 1","phuocDZ 1","123",null,null);
        User user2 = new User(2,"user email2","phone 2","phuocDZ 2","123",null,null);
        List<Post> temp = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Post postT = new Post(i,user1,i+" ố dè "+i,null,null,"2023/4/1");
            temp.add(postT);
        }
        return temp;
    }



}