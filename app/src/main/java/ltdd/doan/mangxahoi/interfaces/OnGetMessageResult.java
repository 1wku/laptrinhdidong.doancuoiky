package ltdd.doan.mangxahoi.interfaces;

import ltdd.doan.mangxahoi.data.dto.response.ListMessageResponse;

public interface OnGetMessageResult {
    void onSuccess(ListMessageResponse result);
    void onError(String error );

}
