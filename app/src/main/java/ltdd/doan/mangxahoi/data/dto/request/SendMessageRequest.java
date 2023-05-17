package ltdd.doan.mangxahoi.data.dto.request;

public class SendMessageRequest {
    public String conversationId;
    public String sendBy;
    public String text;

    public SendMessageRequest(String conversationId, String sendBy, String text) {
        this.conversationId = conversationId;
        this.sendBy = sendBy;
        this.text = text;
    }
}
