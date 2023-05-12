package ltdd.doan.mangxahoi.ui.view.fragment;

import static android.view.View.GONE;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;
import ltdd.doan.mangxahoi.R;
import ltdd.doan.mangxahoi.data.model.User;
import ltdd.doan.mangxahoi.databinding.FragmentPostDetailsBinding;
import ltdd.doan.mangxahoi.databinding.FragmentProfileBinding;
import ltdd.doan.mangxahoi.interfaces.OnGetCheckIsFollowUserResult;
import ltdd.doan.mangxahoi.interfaces.OnToogleFollowResult;
import ltdd.doan.mangxahoi.session.Session;
import ltdd.doan.mangxahoi.ui.view.adapter.PostAdapterProfile;
import ltdd.doan.mangxahoi.ui.viewmodel.ProfileViewModel;
@AndroidEntryPoint
public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    private ProfileViewModel mViewModel;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        binding.setProfileFragment(this);

        Bundle bundle = getArguments();
        String user_id = (bundle == null ) ? Session.getSharedPreference(getContext(),"user_id","") : bundle.getString("user_id") ;

        binding.frgProfileSwipeRefresh.setOnRefreshListener(() -> {
            mViewModel.getUserDetailsById(user_id);
            mViewModel.getPostsByUserId(user_id);
            binding.frgProfileSwipeRefresh.setRefreshing(false);
        });


        mViewModel.getUser().observe(getViewLifecycleOwner(), user -> {
            binding.setUser(user);
            // TODO: 4/18/2023 ảnh
            if (!Objects.equals(user.getAvatar() , "")){
                Glide.with(requireContext())
                        .load(user.getAvatar() )
                        .into(binding.frgProfileUserAvatar);
            }
            if (user_id == Session.getSharedPreference(getContext(),"user_id","") ){
                binding.frgProfileBtnFollow.setVisibility(GONE);
            }
            mViewModel.onCheckIsFollowUser(user_id, new OnGetCheckIsFollowUserResult() {
                @Override
                public void onSuccess(String result) {
                    if ( Objects.equals(result,"YES"))  binding.frgProfileBtnFollow.setText("UNFOLLOW");
                    else  binding.frgProfileBtnFollow.setText("FOLLOW");
                }
                @Override
                public void onError(String error) {
                    System.out.println(error);
                }
            });

        });

        mViewModel.getPosts().observe(getViewLifecycleOwner(), posts -> {
            binding.setPostCount(posts.size());

            PostAdapterProfile postAdapter = new PostAdapterProfile(requireContext(), posts);
            binding.frgProfileRecyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 3));

            binding.setPostAdapter(postAdapter);

        });

        mViewModel.getUserDetailsById(user_id);
        mViewModel.getPostsByUserId(user_id);

        return binding.getRoot();
    }

    // TODO: 4/18/2023
    public void navToFollow(View view, List<User> users) {
    }


    public void toggleFollow(User user) {
        mViewModel.follow(user.getId(), new OnToogleFollowResult() {
            @Override
            public void onSuccess(String result) {
                if ( Objects.equals(result,"user have been unfollowed"))  binding.frgProfileBtnFollow.setText("FOLLOW");
                else  binding.frgProfileBtnFollow.setText("UNFOLLOW");

            }
            @Override
            public void onError(String error) {
                System.out.println(error);
            }
        });
        // update ui
        mViewModel.getUserDetailsById(user.getId());
    }

}