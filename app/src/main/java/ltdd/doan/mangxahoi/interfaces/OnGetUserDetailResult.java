package ltdd.doan.mangxahoi.interfaces;

import ltdd.doan.mangxahoi.data.model.Post;
import ltdd.doan.mangxahoi.data.model.User;

public interface OnGetUserDetailResult {
    void onSuccess(User result);
    void onError(String error );
}