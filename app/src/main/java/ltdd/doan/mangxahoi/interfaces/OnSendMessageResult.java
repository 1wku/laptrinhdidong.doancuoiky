package ltdd.doan.mangxahoi.interfaces;

import org.json.JSONException;

import ltdd.doan.mangxahoi.data.model.Message;

public interface OnSendMessageResult {
    void onSuccess(Message result) throws JSONException;
    void onError(String error);
}
