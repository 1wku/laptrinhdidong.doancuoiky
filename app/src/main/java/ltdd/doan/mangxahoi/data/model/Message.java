package ltdd.doan.mangxahoi.data.model;

import com.google.gson.annotations.SerializedName;

public class Message {
    @SerializedName("_id")
    private String id;
    private String conversationId;
    private String sendBy;
    private String text;
    private Boolean read;

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public Message(String conversationId, String sendBy, String text) {
        this.conversationId = conversationId;
        this.sendBy = sendBy;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getSendBy() {
        return sendBy;
    }

    public void setSendBy(String sendBy) {
        this.sendBy = sendBy;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
