package ltdd.doan.mangxahoi.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Comment implements Parcelable {

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

    public Comment(Integer comment_id, User comment_owner, String comment_content, List<Comment> comment_repliers, List<User> comment_likers) {
        this.comment_id = comment_id;
        this.comment_owner = comment_owner;
        this.comment_content = comment_content;
        this.comment_repliers = comment_repliers;
        this.comment_likers = comment_likers;
    }

    @SerializedName("comment_likers")
    private Integer comment_id;
    @SerializedName("comment_likers")
    private User comment_owner;
    @SerializedName("comment_likers")
    private String comment_content;
    @SerializedName("comment_likers")
    private List<Comment> comment_repliers;
    @SerializedName("comment_likers")
    private List<User> comment_likers;

    protected Comment(Parcel in) {
        if (in.readByte() == 0) {
            comment_id = null;
        } else {
            comment_id = in.readInt();
        }
        comment_owner = in.readParcelable(User.class.getClassLoader());
        comment_content = in.readString();
        comment_repliers = in.createTypedArrayList(Comment.CREATOR);
        comment_likers = in.createTypedArrayList(User.CREATOR);
    }

    public static final Creator<Comment> CREATOR = new Creator<Comment>() {
        @Override
        public Comment createFromParcel(Parcel in) {
            return new Comment(in);
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        if (comment_id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(comment_id);
        }
        parcel.writeParcelable(comment_owner, i);
        parcel.writeString(comment_content);
        parcel.writeTypedList(comment_repliers);
        parcel.writeTypedList(comment_likers);
    }
}
