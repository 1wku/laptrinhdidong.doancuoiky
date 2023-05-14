package ltdd.doan.mangxahoi.interfaces;

import java.util.List;

import ltdd.doan.mangxahoi.data.model.Conversation;
import ltdd.doan.mangxahoi.data.model.Post;

public interface OnGetConversationResult {
    void onSuccess(List<Conversation> result);
    void onError(String error );
}
