package ltdd.doan.mangxahoi.ui.view.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import dagger.hilt.android.AndroidEntryPoint;
import ltdd.doan.mangxahoi.databinding.FragmentProfileBinding;
import ltdd.doan.mangxahoi.ui.viewmodel.ProfileViewModel;

@AndroidEntryPoint
public class SettingFragment  extends Fragment {
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

}
