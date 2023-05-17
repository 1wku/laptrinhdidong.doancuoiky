package ltdd.doan.mangxahoi.ui.viewmodel;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import ltdd.doan.mangxahoi.data.repository.UserRepository;

@HiltViewModel
public class MainViewModel extends ViewModel {
    private UserRepository uRepo;

    @Inject
    public MainViewModel(UserRepository uRepo) {
        this.uRepo = uRepo;
    }

    public void removeSessionUser() {
        uRepo.removeSessionUser();
    }


}
