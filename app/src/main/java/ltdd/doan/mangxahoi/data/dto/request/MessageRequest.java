package ltdd.doan.mangxahoi.data.dto.request;

public class MessageRequest {
    public String senderId;
    public String receiverId;
    public String message;

    public MessageRequest(String senderId, String receiverId, String message) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
    }
}
