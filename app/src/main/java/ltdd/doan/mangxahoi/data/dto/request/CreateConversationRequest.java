package ltdd.doan.mangxahoi.data.dto.request;

public class CreateConversationRequest {
    public String senderId;
    public String receiverId;

    public CreateConversationRequest(String senderId, String receiverId) {
        this.senderId = senderId;
        this.receiverId = receiverId;
    }
}
