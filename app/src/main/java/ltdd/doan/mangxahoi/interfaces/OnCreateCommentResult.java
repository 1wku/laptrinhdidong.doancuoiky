package ltdd.doan.mangxahoi.interfaces;

import ltdd.doan.mangxahoi.data.model.Comment;
import ltdd.doan.mangxahoi.data.model.Post;
public interface OnCreateCommentResult {

    void onSuccess(Comment data);
    void onError(String error );
}
