package ltdd.doan.mangxahoi.ui.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import ltdd.doan.mangxahoi.data.model.User;
import ltdd.doan.mangxahoi.data.repository.UserRepository;
import ltdd.doan.mangxahoi.interfaces.OnFilterUserResult;

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
        uRepo.filterUsersByName(user_name, new OnFilterUserResult() {
            @Override
            public void onSuccess(List<User> result) {
                System.out.println(users);
                users.setValue(result);
            }
            @Override
            public void onError(String error) {
                Log.e("FilterUsersByName",error );
            }
        });
    }
}