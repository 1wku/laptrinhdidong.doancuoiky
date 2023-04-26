package ltdd.doan.mangxahoi.interfaces;

import ltdd.doan.mangxahoi.data.model.Post;
import okhttp3.ResponseBody;

public interface OnGetPostByIdResult {
    void onSuccess(Post resultPost);
    void onError(String error );
}