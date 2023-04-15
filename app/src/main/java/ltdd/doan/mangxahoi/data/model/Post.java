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
    private Integer id;
    private Integer owner;
    private String photo;
    private String content;
    private List<Integer> like;
    private String created_at;

    public Post(Integer id, Integer owner, String photo, String content, List<Integer> like, String created_at) {
        this.id = id;
        this.owner = owner;
        this.photo = photo;
        this.content = content;
        this.like = like;
        this.created_at = created_at;
    }

    public Post() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getOwner() {
        User owner = new User(this.owner,randomS(10),null,null,randomS(10),null,null,null,null,null,null,null);
        return owner;
    }

    public void setOwner(Integer owner) {
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

    public List<Integer> getLike() {
        return like;
    }

    public void setLike(List<Integer> like) {
        this.like = like;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public Post getEx(User user){
        Integer random = new Random().nextInt(100);
        Post post = new Post(random,user.getId(),null,randomS(50),null, Date.from(Instant.now()).toString());
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
