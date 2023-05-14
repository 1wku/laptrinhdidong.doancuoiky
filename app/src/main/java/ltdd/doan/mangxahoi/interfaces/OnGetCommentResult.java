package ltdd.doan.mangxahoi.interfaces;

import java.util.List;

import ltdd.doan.mangxahoi.data.model.Comment;
import ltdd.doan.mangxahoi.data.model.Conversation;

public interface OnGetCommentResult {
    void onSuccess(List<Comment> result);
    void onError(String error );
}
