package ltdd.doan.mangxahoi.interfaces;

import java.util.List;

import ltdd.doan.mangxahoi.data.model.User;

public interface OnToogleFollowResult {
    void onSuccess(List<String> result);
    void onError(String error );
}