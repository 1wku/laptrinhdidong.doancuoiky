package ltdd.doan.mangxahoi.interfaces;

import ltdd.doan.mangxahoi.data.model.Conversation;

public interface OnGetOneConversationResult {
    public void onSuccess(Conversation conversation);
    public  void onError(String error);
}
