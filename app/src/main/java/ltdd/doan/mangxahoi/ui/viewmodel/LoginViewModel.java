package ltdd.doan.mangxahoi.ui.viewmodel;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import ltdd.doan.mangxahoi.data.repository.UserRepository;
import ltdd.doan.mangxahoi.interfaces.OnLoggedInResult;

@HiltViewModel
public class LoginViewModel extends ViewModel {


    private UserRepository uRepo;

    @Inject
    public LoginViewModel(UserRepository uRepo) {
        this.uRepo = uRepo;
    }

    public void getLastSessionUser(OnLoggedInResult onLoggedInResult) {
        uRepo.getLastSessionUser(onLoggedInResult);
    }

    public void login(String email, String user_password, OnLoggedInResult onLoggedInResult) {
        uRepo.login(email, user_password,onLoggedInResult);
    }
}