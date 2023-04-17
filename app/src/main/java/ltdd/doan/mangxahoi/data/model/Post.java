package ltdd.doan.mangxahoi.data.model;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import ltdd.doan.mangxahoi.data.Utils;

public class Post  {
    private Integer id;
    private Integer owner;
    private String photo;
    private String content;
    private List<Integer> likers;
    private List<Integer> comments;
    private String created_at;

    public Post(Integer id, Integer owner, String photo, String content, List<Integer> likers, List<Integer> comments, String created_at) {
        this.id = id;
        this.owner = owner;
        this.photo = photo;
        this.content = content;
        this.likers = likers;
        this.comments = comments;
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

    // TODO: 4/18/2023
    public User getOwner() {
        User owner = new User(this.owner,Utils.randomS(10),null,null,Utils.randomS(10),null,null,null,null,null,null,null);
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

    public List<Integer> getLikers() {
        return likers;
    }

    public void setLikers(List<Integer> likers) {
        this.likers = likers;
    }

    public List<Integer> getComments() {
        return comments;
    }

    public void setComments(List<Integer> comments) {
        this.comments = comments;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public Post getEx(User user){
        return new Post(Utils.random(),user.getId(),null,Utils.randomS(50),null, null, Date.from(Instant.now()).toString());
    }


}
