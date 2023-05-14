package ltdd.doan.mangxahoi.interfaces;

import ltdd.doan.mangxahoi.data.dto.response.LikePostResponse;

public interface OnLikePostResult {
    void onSuccess(LikePostResponse result);
    void onError(String error );
}