package ltdd.doan.mangxahoi.ui.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ltdd.doan.mangxahoi.R;
import ltdd.doan.mangxahoi.ui.viewmodel.NewPostViewModel;

public class NewPostFragment extends Fragment {

    private NewPostViewModel mViewModel;

    public static NewPostFragment newInstance() {
        return new NewPostFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_post, container, false);
    }



}