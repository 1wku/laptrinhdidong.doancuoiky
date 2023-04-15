package ltdd.doan.mangxahoi.ui.view.fragment;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ltdd.doan.mangxahoi.R;
import ltdd.doan.mangxahoi.databinding.FragmentPostDetailsBinding;
import ltdd.doan.mangxahoi.databinding.FragmentProfileBinding;
import ltdd.doan.mangxahoi.ui.view.adapter.PostAdapterProfile;
import ltdd.doan.mangxahoi.ui.viewmodel.ProfileViewModel;

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
        int profile_id = (bundle == null ) ? 1 : bundle.getInt("profile_id");
        binding.setUser(mViewModel.getUserByID(profile_id));

        PostAdapterProfile postAdapterProfile = new PostAdapterProfile(requireContext(), mViewModel);
        postAdapterProfile.setData(profile_id);
        binding.setPostAdapter(postAdapterProfile);
        binding.frgProfileRecyclerView.setLayoutManager(new GridLayoutManager(requireContext(),3));


        return binding.getRoot();
    }

}