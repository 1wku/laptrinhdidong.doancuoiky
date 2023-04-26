package ltdd.doan.mangxahoi.interfaces;

import java.util.List;

import ltdd.doan.mangxahoi.data.model.Post;

public interface OnGetPostsByUserResult {
    void onSuccess(List<Post> result);
    void onError(String error);
}