package ltdd.doan.mangxahoi.interfaces;

import java.util.List;

import ltdd.doan.mangxahoi.data.model.User;

public interface OnFilterUserResult {
    void onSuccess(List<User> result);
    void onError(String error );
}