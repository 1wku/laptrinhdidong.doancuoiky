package ltdd.doan.mangxahoi.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import ltdd.doan.mangxahoi.data.model.User;
import ltdd.doan.mangxahoi.data.repository.UserRepository;

@HiltViewModel
public class SearchViewModel extends ViewModel {
    private UserRepository uRepo;
    private MutableLiveData<List<User>> users;

    @Inject
    public SearchViewModel(UserRepository uRepo) {
        this.uRepo = uRepo;
        this.users = uRepo.getUsers();
    }

    public MutableLiveData<List<User>> getUsers() {
        return users;
    }

    public void filterUsersByName(String user_name) {
        uRepo.filterUsersByName(user_name);
    }
}