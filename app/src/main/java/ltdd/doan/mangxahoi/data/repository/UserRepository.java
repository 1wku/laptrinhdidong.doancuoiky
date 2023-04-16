package ltdd.doan.mangxahoi.data.repository;

import android.content.Context;

import ltdd.doan.mangxahoi.data.model.User;
import ltdd.doan.mangxahoi.session.Session;

public class UserRepository {

    private final Context context;

    private User user;

    public UserRepository(Context context) {
        this.context = context;
    }

    public void getLastSessionUser() {
        String user_name = Session.getSharedPreference(context, "user_name", "");
        String user_password = Session.getSharedPreference(context, "user_password", "");

        if (!user_name.isEmpty() && !user_password.isEmpty()) {
            login(user_name, user_password);
        }
    }

    private void setLastSessionUser(String user_name, String user_password) {
        Session.setSharedPreference(context, "user_name", user_name);
        Session.setSharedPreference(context, "user_password", user_password);
    }

    public void login(String user_name, String user_password){
        if (user_name == "phuoc" || user_password == "123"){
            Session.ACTIVE_USER = user.getEx();
            setLastSessionUser(user_name,user_password);
        }
    }

    public void register(String user_name, String user_password){
        if (user_name == "phuoc" || user_password == "123"){
            Session.ACTIVE_USER = user.getEx();
            setLastSessionUser(user_name,user_password);
        }
    }



}
