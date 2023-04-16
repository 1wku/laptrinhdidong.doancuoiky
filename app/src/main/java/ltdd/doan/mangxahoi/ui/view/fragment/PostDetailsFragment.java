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
import ltdd.doan.mangxahoi.databinding.FragmentPostDetailsBinding;
import ltdd.doan.mangxahoi.ui.viewmodel.PostDetailsViewModel;
@AndroidEntryPoint
public class PostDetailsFragment extends Fragment {

    private FragmentPostDetailsBinding binding;

    private PostDetailsViewModel mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PostDetailsViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_post_details, container, false);
        binding.setPostDetailsFragment(this);

        int post_id = getArguments().getInt("post_id");
        binding.setPost(mViewModel.getPostDetailsById(post_id));
        return binding.getRoot();
    }


}