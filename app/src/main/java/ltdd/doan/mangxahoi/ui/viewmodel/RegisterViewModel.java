package ltdd.doan.mangxahoi.ui.viewmodel;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import ltdd.doan.mangxahoi.data.repository.UserRepository;
@HiltViewModel
public class RegisterViewModel extends ViewModel {

    private UserRepository uRepo;

    @Inject
    public RegisterViewModel(UserRepository uRepo) {
        this.uRepo = uRepo;
    }

    public void register(String user_name, String user_password) {
        uRepo.login(user_name, user_password);
    }

}