package ltdd.doan.mangxahoi.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Random;

import kotlin.random.URandomKt;
import ltdd.doan.mangxahoi.R;

public class User {
    private Integer user_id;
    private String user_email;
    private String user_phone_No;
    private String user_full_name;
    private String user_password;
    private List<User> followers;
    private List<User> following;

    public User(Integer user_id, String user_email, String user_phone_No, String user_full_name, String user_password, List<User> followers, List<User> following) {
        this.user_id = user_id;
        this.user_email = user_email;
        this.user_phone_No = user_phone_No;
        this.user_full_name = user_full_name;
        this.user_password = user_password;
        this.followers = followers;
        this.following = following;
    }

    public User() {
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_phone_No() {
        return user_phone_No;
    }

    public void setUser_phone_No(String user_phone_No) {
        this.user_phone_No = user_phone_No;
    }

    public String getUser_full_name() {
        return user_full_name;
    }

    public void setUser_full_name(String user_full_name) {
        this.user_full_name = user_full_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public User getEx(){
        Random random = new Random();
        User user = new User(11,"odeee","phone",randomS(),"123",null,null);
        return user;
    }
    public String randomS(){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
