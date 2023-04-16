package ltdd.doan.mangxahoi.ui.viewmodel;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import ltdd.doan.mangxahoi.data.repository.UserRepository;
@HiltViewModel
public class LoginViewModel extends ViewModel {


    private UserRepository uRepo;

    @Inject
    public LoginViewModel(UserRepository uRepo) {
        this.uRepo = uRepo;
    }

    public void getLastSessionUser() {
        uRepo.getLastSessionUser();
    }

    public void login(String user_name, String user_password) {
        uRepo.login(user_name, user_password);
    }
}