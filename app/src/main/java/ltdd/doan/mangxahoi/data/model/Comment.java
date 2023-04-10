package ltdd.doan.mangxahoi.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Comment {
    private Integer comment_id;
    private User comment_owner;
    private String comment_content;
    private List<Comment> comment_repliers;
    private List<User> comment_likers;

    public Comment(Integer comment_id, User comment_owner, String comment_content, List<Comment> comment_repliers, List<User> comment_likers) {
        this.comment_id = comment_id;
        this.comment_owner = comment_owner;
        this.comment_content = comment_content;
        this.comment_repliers = comment_repliers;
        this.comment_likers = comment_likers;
    }

    public Comment() {
    }

    public Integer getComment_id() {
        return comment_id;
    }

    public void setComment_id(Integer comment_id) {
        this.comment_id = comment_id;
    }

    public User getComment_owner() {
        return comment_owner;
    }

    public void setComment_owner(User comment_owner) {
        this.comment_owner = comment_owner;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public List<Comment> getComment_repliers() {
        return comment_repliers;
    }

    public void setComment_repliers(List<Comment> comment_repliers) {
        this.comment_repliers = comment_repliers;
    }

    public List<User> getComment_likers() {
        return comment_likers;
    }

    public void setComment_likers(List<User> comment_likers) {
        this.comment_likers = comment_likers;
    }
}
