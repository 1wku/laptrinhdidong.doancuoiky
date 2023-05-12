package ltdd.doan.mangxahoi.interfaces;

import ltdd.doan.mangxahoi.data.model.Post;

public interface OnCreatePostResult {
    void onSuccess(Post data);
    void onError(String error );
}
