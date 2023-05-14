package ltdd.doan.mangxahoi.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Conversation {
    @SerializedName("_id")
    private String id;

    private List<String> members ;
    private String created_at;

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

    private String updated_at;

    public Conversation(String id, List<String> members, String created_at, String updated_at) {
        this.id = id;
        this.members = members;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Conversation(String id, List<String> members) {
        this.id = id;
        this.members = members;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }
}
