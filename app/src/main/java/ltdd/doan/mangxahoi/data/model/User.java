package ltdd.doan.mangxahoi.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User implements Parcelable {


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


    public User(Integer user_id, String user_email, String user_phone_No, String user_full_name, String user_password, List<User> followers, List<User> following) {
        this.user_id = user_id;
        this.user_email = user_email;
        this.user_phone_No = user_phone_No;
        this.user_full_name = user_full_name;
        this.user_password = user_password;
        this.followers = followers;
        this.following = following;
    }

    @SerializedName("user_id")
    private Integer user_id;
    @SerializedName("user_email")
    private String user_email;
    @SerializedName("user_phone_No")

    private String user_phone_No;
    @SerializedName("user_full_name")
    private String user_full_name;
    @SerializedName("user_password")
    private String user_password;
    @SerializedName("followers")
    private List<User> followers;
    @SerializedName("following")
    private List<User> following;


    protected User(Parcel in) {
        if (in.readByte() == 0) {
            user_id = null;
        } else {
            user_id = in.readInt();
        }
        user_email = in.readString();
        user_phone_No = in.readString();
        user_full_name = in.readString();
        user_password = in.readString();
        followers = in.createTypedArrayList(User.CREATOR);
        following = in.createTypedArrayList(User.CREATOR);
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        if (user_id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(user_id);
        }
        parcel.writeString(user_email);
        parcel.writeString(user_phone_No);
        parcel.writeString(user_full_name);
        parcel.writeString(user_password);
        parcel.writeTypedList(followers);
        parcel.writeTypedList(following);
    }
}
