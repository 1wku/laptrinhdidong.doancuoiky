package ltdd.doan.mangxahoi.ui.view.fragment;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import dagger.hilt.android.AndroidEntryPoint;
import ltdd.doan.mangxahoi.R;
import ltdd.doan.mangxahoi.databinding.FragmentRegisterBinding;
import ltdd.doan.mangxahoi.ui.view.activity.MainActivity;
import ltdd.doan.mangxahoi.ui.viewmodel.RegisterViewModel;
@AndroidEntryPoint
public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding binding;

    private RegisterViewModel mViewModel;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false);


        return binding.getRoot();
    }

    public void register(){
        String username = binding.frgRegisterTxtUserName.toString();
        String password = binding.frgRegisterTxtUserPassword.toString();

        if (username.isEmpty()||password.isEmpty())
            Toast.makeText(requireContext(), "Vui long điền dầy đủ thông tin", Toast.LENGTH_SHORT).show();
        else {
            mViewModel.register(username,password);
            Intent intent = new Intent(requireContext(), MainActivity.class);
            startActivity(intent);
            requireActivity().finish();
        }
    }

    public void navToLogin(){
        Navigation.findNavController(binding.frgRegisterLblNavToLogin).navigate(R.id.registerToLogin);
    }
}