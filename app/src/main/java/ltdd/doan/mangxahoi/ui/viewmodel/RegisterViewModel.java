package ltdd.doan.mangxahoi.ui.viewmodel;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import ltdd.doan.mangxahoi.data.repository.UserRepository;
import ltdd.doan.mangxahoi.interfaces.OnRegisterResult;

@HiltViewModel
public class RegisterViewModel extends ViewModel {

    private UserRepository uRepo;

    @Inject
    public RegisterViewModel(UserRepository uRepo) {
        this.uRepo = uRepo;
    }

    public void register(String user_name, String user_password, String user_mail, OnRegisterResult onRegisterResult) {
        uRepo.register(user_name, user_password,user_mail,onRegisterResult);
    }

}