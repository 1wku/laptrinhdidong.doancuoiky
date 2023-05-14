package ltdd.doan.mangxahoi.interfaces;

import ltdd.doan.mangxahoi.data.model.Post;
import ltdd.doan.mangxahoi.data.model.User;

public interface OnUpdatePostResult {
    void onSuccess(Post result);
    void onError(String error );
}
