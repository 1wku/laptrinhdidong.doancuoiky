package ltdd.doan.mangxahoi.ui.view.fragment;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ltdd.doan.mangxahoi.R;
import ltdd.doan.mangxahoi.ui.viewmodel.PostDetailsViewModel;

public class PostDetailsFragment extends Fragment {

    private PostDetailsViewModel mViewModel;

    public static PostDetailsFragment newInstance() {
        return new PostDetailsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_post_details, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PostDetailsViewModel.class);
        // TODO: Use the ViewModel
    }

}