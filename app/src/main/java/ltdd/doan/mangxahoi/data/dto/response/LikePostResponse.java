package ltdd.doan.mangxahoi.data.dto.response;

import com.google.gson.annotations.Expose;

import java.util.List;

public class LikePostResponse {

    @Expose
    public String postId ;
    @Expose
    public List<String> likes;
}
