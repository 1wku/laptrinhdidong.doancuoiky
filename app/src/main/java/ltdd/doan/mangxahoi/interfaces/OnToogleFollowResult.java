package ltdd.doan.mangxahoi.interfaces;

import ltdd.doan.mangxahoi.data.model.User;

public interface OnToogleFollowResult {
    void onSuccess(String result);
    void onError(String error );
}