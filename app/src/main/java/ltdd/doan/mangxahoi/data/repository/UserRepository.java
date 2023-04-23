package ltdd.doan.mangxahoi.data.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ltdd.doan.mangxahoi.api.ApiInterface;
import ltdd.doan.mangxahoi.data.dto.request.LoginRequest;
import ltdd.doan.mangxahoi.data.dto.request.RegisterRequest;
import ltdd.doan.mangxahoi.data.model.User;
import ltdd.doan.mangxahoi.interfaces.OnLoggedInResult;
import ltdd.doan.mangxahoi.interfaces.OnRegisterResult;
import ltdd.doan.mangxahoi.session.Session;
import ltdd.doan.mangxahoi.ui.view.fragment.RegisterFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private final ApiInterface apiService;
    private final Context context;
    private MutableLiveData<List<User>> users;
    private MutableLiveData<User> user; //
    private MutableLiveData<String> message; // messages from response
    private MutableLiveData<Boolean> status; // status for navigation

    public UserRepository(Context context, ApiInterface apiService) {
        this.apiService = apiService;
        this.context = context;

        users = new MutableLiveData<>();
        user = new MutableLiveData<>();
        message = new MutableLiveData<>();
        status = new MutableLiveData<>();
    }

    public MutableLiveData<List<User>> getUsers() {
        users = new MutableLiveData<>();
        return users;
    }

    public MutableLiveData<User> getUser() {
        user = new MutableLiveData<>();
        return user;
    }

    public MutableLiveData<String> getMessage() {
        message = new MutableLiveData<>();
        return message;
    }

    public MutableLiveData<Boolean> getStatus() {
        status = new MutableLiveData<>();
        return status;
    }

    public void getLastSessionUser(OnLoggedInResult onLoggedInResult) {
        removeSessionUser();

        String user_email = Session.getSharedPreference(context, "user_email", "");
        String user_password = Session.getSharedPreference(context, "user_password", "");

        if (!user_email.isEmpty() && !user_password.isEmpty()) {
            login(user_email, user_password,onLoggedInResult);
        }

    }

    private void setLastSessionUser(String user_name, String user_password) {
        Session.setSharedPreference(context, "user_email", user_name);
        Session.setSharedPreference(context, "user_password", user_password);
    }
    private  void removeSessionUser(){
        Session.removeSharedPreference(context,"user_email");
        Session.removeSharedPreference(context,"user_password");
    }

    // TODO: 4/18/2023
    public void login(String user_email, String user_password, OnLoggedInResult onLoggedInResult){
        apiService.login(new LoginRequest(user_email, user_password)).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.code()==200) {
                    Session.ACTIVE_USER = response.body();
                    setLastSessionUser(user_email,user_password);
                    Log.d("LoginActivity", "LOGIN SUCCESS");
                    onLoggedInResult.onSuccess();
                }else {
                    int statusCode  = response.code();
                    Log.d("LoginActivity", response.message());
                    onLoggedInResult.onError();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("LoginActivity", "LOGIN FAILSE");
                onLoggedInResult.onError();
            }
        });
    }

    // TODO: 4/18/2023
    public void register(String user_name, String user_password, String user_mail, OnRegisterResult onRegisterResult){
            apiService.register(new RegisterRequest(user_mail,user_name, user_password)).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if(response.isSuccessful()) {
                        Session.ACTIVE_USER = response.body();
                        setLastSessionUser(user_mail,user_password);
                        Log.d("Authentication", "REGISTER SUCCESS");
                        onRegisterResult.onSuccess();
                    }else {
                        Log.d("Authentication", response.message());
                        onRegisterResult.onError();
                    }
                }
                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.d("Authentication", "REGISTER FAILE");
                    onRegisterResult.onError();
                }
            });

    }

    // TODO: 4/18/2023
    public void getUserDetailsById(int user_id){
        user.setValue(new User().getEx(user_id));
    }

    // TODO: 4/18/2023
    public void updateUser(User user_update){
    }

    // TODO: 4/18/2023
    public void follow(int user_id){
    }

    // TODO: 4/18/2023
    public void unfollow(int user_id){
    }

    // TODO: 4/21/2023
    public void filterUsersByName(String user_name){
        List<User> temp = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            temp.add(new User().getEx());
        }
        users.setValue(temp);
    }
}
