package ltdd.doan.mangxahoi.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import ltdd.doan.mangxahoi.data.Utils;

public class User {

    @SerializedName("_id")
    private String id;
    @Expose
    private String username;
    @Expose
    private String email;
    @Expose
    private String password;
    private String full_name;
    @Expose
    private String avatar;
    private String phone_no;
    @SerializedName("from")
    private String address;
    private String relationship;
    @SerializedName("description")
    private String bio;
    private List<String> followers;
    private List<String> followings;

    public User(String id, String username, String email, String password, String full_name, String avatar, String phone_no, String address, String relationship, String bio, List<String> followers, List<String> following) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.full_name = full_name;
        this.avatar = avatar;
        this.phone_no = phone_no;
        this.address = address;
        this.relationship = relationship;
        this.bio = bio;
        this.followers = followers;
        this.followings = following;
    }

    public User() {
    }

    public User(String avatar, String user_id) {
        this.avatar = avatar;
        this.id = user_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<String> getFollowers() {
        return followers;
    }

    public void setFollowers(List<String> followers) {
        this.followers = followers;
    }

    public List<String> getFollowing() {
        return followings;
    }

    public void setFollowing(List<String> following) {
        this.followings = following;
    }

}
