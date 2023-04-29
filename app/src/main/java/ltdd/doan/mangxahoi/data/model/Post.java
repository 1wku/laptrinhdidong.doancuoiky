package ltdd.doan.mangxahoi.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import ltdd.doan.mangxahoi.data.Utils;

public class Post  {

    @SerializedName("_id")
    private String id;
    @SerializedName("userId")

    private String owner;
    @SerializedName("img")
    private String photo;
    @SerializedName("ownerData")
    private User ownerData;
    @Expose
    private String content;
    @SerializedName("likes")
    private List<String> likers;
    private List<String> comments;
    private String created_at;
    private String updated_at;

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public Post(String id, String owner, String photo, String content, List<String> likers, List<String> comments, String created_at, String updated_at) {
        this.id = id;
        this.owner = owner;
        this.photo = photo;
        this.content = content;
        this.likers = likers;
        this.comments = comments;
        this.created_at = created_at;
        this.updated_at= updated_at;
    }

    public Post() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // TODO: 4/18/2023
    public String getOwner() {
        return this.owner;
    }

    public User getOwnerData(){
        return this.ownerData;

    }



    public void setOwnerData(User user){
        this.ownerData = user ;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getLikers() {
        return likers;
    }

    public void setLikers(List<String> likers) {
        this.likers = likers;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

//    public Post getEx(User user){
//        return new Post(Utils.random(),user.getId(),null,Utils.randomS(50),null, null, Date.from(Instant.now()).toString());
//    }


}
