package ltdd.doan.mangxahoi.data.model;

import java.util.List;
import ltdd.doan.mangxahoi.data.Utils;

public class User {
    private Integer id;
    private String username;
    private String email;
    private String password;
    private String full_name;
    private String avatar;
    private String phone_no;
    private String address;
    private String relationship;
    private String bio;
    private List<Integer> followers;
    private List<Integer> following;

    public User(Integer id, String username, String email, String password, String full_name, String avatar, String phone_no, String address, String relationship, String bio, List<Integer> followers, List<Integer> following) {
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
        this.following = following;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public List<Integer> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Integer> followers) {
        this.followers = followers;
    }

    public List<Integer> getFollowing() {
        return following;
    }

    public void setFollowing(List<Integer> following) {
        this.following = following;
    }

    public User getEx(){
        return new User(Utils.random(), Utils.randomS(), Utils.randomS(), Utils.randomS(), Utils.randomS(),null,null,null,null,null,null,null);
    }
    public User getEx(int id){

        return new User(id, Utils.randomS(), Utils.randomS(), Utils.randomS(), Utils.randomS(),null,null,null,null,null,null,null);
    }
}
