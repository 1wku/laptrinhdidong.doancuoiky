package ltdd.doan.mangxahoi.data.dto.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import ltdd.doan.mangxahoi.data.model.Post;

public class ListFeedResponse {
    @Expose
    public List<Post> timeline ;

    @Expose
    public Integer next =0 ;
}
