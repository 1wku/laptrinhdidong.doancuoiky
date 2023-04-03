package ltdd.doan.mangxahoi.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Post implements Parcelable {


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

    public Post(Integer post_id, User post_owner, String post_content, List<Comment> post_commnents, List<User> post_likers, String created_at) {
        this.post_id = post_id;
        this.post_owner = post_owner;
        this.post_content = post_content;
        this.post_commnents = post_commnents;
        this.post_likers = post_likers;
        this.created_at = created_at;
    }

    @SerializedName("post_id")
    private Integer post_id;
    @SerializedName("post_owner")
    private User post_owner;
    @SerializedName("post_content")
    private String post_content;
    @SerializedName("post_commnents")
    private List<Comment> post_commnents;
    @SerializedName("post_likers")
    private List<User> post_likers;
    @SerializedName("created_at")
    private String created_at;

    protected Post(Parcel in) {
        if (in.readByte() == 0) {
            post_id = null;
        } else {
            post_id = in.readInt();
        }
        post_owner = in.readParcelable(User.class.getClassLoader());
        post_content = in.readString();
        post_commnents = in.createTypedArrayList(Comment.CREATOR);
        post_likers = in.createTypedArrayList(User.CREATOR);
        created_at = in.readString();
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        if (post_id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(post_id);
        }
        parcel.writeParcelable(post_owner, i);
        parcel.writeString(post_content);
        parcel.writeTypedList(post_commnents);
        parcel.writeTypedList(post_likers);
        parcel.writeString(created_at);
    }
}
