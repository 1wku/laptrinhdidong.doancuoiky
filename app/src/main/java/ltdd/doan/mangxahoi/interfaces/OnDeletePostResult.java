package ltdd.doan.mangxahoi.interfaces;

import ltdd.doan.mangxahoi.data.model.Post;

public interface OnDeletePostResult {
    void onSuccess(String result);
    void onError(String error );
}
