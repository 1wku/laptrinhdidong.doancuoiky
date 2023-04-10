package ltdd.doan.mangxahoi.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Post  {
    private Integer post_id;
    private User post_owner;
    private String post_content;
    private List<Comment> post_commnents;
    private List<User> post_likers;
    private String created_at;

    public Post(Integer post_id, User post_owner, String post_content, List<Comment> post_commnents, List<User> post_likers, String created_at) {
        this.post_id = post_id;
        this.post_owner = post_owner;
        this.post_content = post_content;
        this.post_commnents = post_commnents;
        this.post_likers = post_likers;
        this.created_at = created_at;
    }
    public Post() {
    }

    public Integer getPost_id() {
        return post_id;
    }

    public void setPost_id(Integer post_id) {
        this.post_id = post_id;
    }

    public User getPost_owner() {
        return post_owner;
    }

    public void setPost_owner(User post_owner) {
        this.post_owner = post_owner;
    }

    public String getPost_content() {
        return post_content;
    }

    public void setPost_content(String post_content) {
        this.post_content = post_content;
    }

    public List<Comment> getPost_commnents() {
        return post_commnents;
    }

    public void setPost_commnents(List<Comment> post_commnents) {
        this.post_commnents = post_commnents;
    }

    public List<User> getPost_likers() {
        return post_likers;
    }

    public void setPost_likers(List<User> post_likers) {
        this.post_likers = post_likers;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public Post getEx(User user){
        Random random = new Random();
        Post post = new Post(11,user.getEx(),randomS(50),null,null, Date.from(Instant.now()).toString());
        return post;
    }

    public String randomS(int length ){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = length;
        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
