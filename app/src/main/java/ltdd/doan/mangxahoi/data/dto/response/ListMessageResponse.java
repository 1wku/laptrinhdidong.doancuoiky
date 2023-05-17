package ltdd.doan.mangxahoi.data.dto.response;

import com.google.gson.annotations.Expose;

import java.util.List;

import ltdd.doan.mangxahoi.data.model.Message;
import ltdd.doan.mangxahoi.data.model.Post;

public class ListMessageResponse {
    @Expose
    public List<Message> messages ;

    @Expose
    public Integer next =0 ;
}
