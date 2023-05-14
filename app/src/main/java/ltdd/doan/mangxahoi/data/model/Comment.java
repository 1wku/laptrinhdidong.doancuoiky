package ltdd.doan.mangxahoi.data.model;


import com.google.gson.annotations.SerializedName;

import java.time.Instant;
import java.util.Date;

import ltdd.doan.mangxahoi.data.Utils;

public class Comment {

    @SerializedName("_id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public Comment(String post_id, String owner, String content) {
        this.post_id = post_id;
        this.owner = owner;
        this.content = content;
    }

    public Comment(String id, String post_id, String owner, String text, String created_at) {
        this.id = id;
        this.post_id = post_id;
        this.owner = owner;
        this.content = text;
        this.created_at = created_at;
    }

    @SerializedName("postId")
    private String post_id;

    @SerializedName("createBy")
    private String owner;
    private String  content;
    private String created_at;

    private String createdAt;
    private String updatedAt;

    public String getContent() {
        return content;
    }

    @SerializedName("ownerData")
    private User ownerData;

    public User getOwnerData() {
        return ownerData;
    }

    public void setOwnerData(User ownerData) {
        this.ownerData = ownerData;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }


}
