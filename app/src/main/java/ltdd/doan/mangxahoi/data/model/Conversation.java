package ltdd.doan.mangxahoi.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

import ltdd.doan.mangxahoi.session.Session;

public class Conversation {
    @SerializedName("_id")
    private String id;

    private List<User> members ;

    private String created_at;

    private Message lastMessage;

    public Message getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(Message lastMessage) {
        this.lastMessage = lastMessage;
    }

    public User getPartner() {
        return partner;
    }

    public void setPartner(User partner) {
        this.partner = partner;
    }

    private User partner;




    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    private String updated_at;

    public Conversation(String id, List<User> members, String created_at, String updated_at) {
        this.id = id;
        this.members = members;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}
