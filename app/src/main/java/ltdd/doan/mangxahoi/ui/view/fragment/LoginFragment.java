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
import ltdd.doan.mangxahoi.databinding.FragmentLoginBinding;
import ltdd.doan.mangxahoi.interfaces.OnLoggedInResult;
import ltdd.doan.mangxahoi.session.Session;
import ltdd.doan.mangxahoi.ui.view.activity.MainActivity;
import ltdd.doan.mangxahoi.ui.viewmodel.LoginViewModel;
@AndroidEntryPoint
public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private LoginViewModel mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_login, container, false);
        binding.setLoginFragment(this);
        mViewModel.getLastSessionUser(new OnLoggedInResult() {
            @Override
            public void onSuccess() {
                Intent intent = new Intent(requireContext(), MainActivity.class);
                startActivity(intent);
                requireActivity().finish();
            }
            @Override
            public void onError() {

            }
        });
        return binding.getRoot();
    }

    public void Login(){
        String email = binding.frgLoginTxtMail.getEditableText().toString();
        String password = (String) binding.frgLoginTxtUserPassword.getEditableText().toString();
        if (email.isEmpty()||password.isEmpty())
            Toast.makeText(requireContext(), "Vui lòng điền đầy thông tin", Toast.LENGTH_SHORT).show();
        else {
            mViewModel.login(email, password, new OnLoggedInResult() {
                @Override
                public void onSuccess() {
                    Intent intent = new Intent(requireContext(), MainActivity.class);
                    startActivity(intent);
                    requireActivity().finish();
                    Toast.makeText(requireContext(), "Đăng nhập thành công.", Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onError() {
                    Toast.makeText(requireContext(), "Email hoặc mật khẩu không tồn tại. Vui lòng thử lại sau.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void navToRegister() {
        Navigation.findNavController(binding.frgLoginLblNavToRegister).navigate(R.id.loginToRegis);
    }

}