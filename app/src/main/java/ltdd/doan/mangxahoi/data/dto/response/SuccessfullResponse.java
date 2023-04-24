package ltdd.doan.mangxahoi.data.dto.response;

import com.google.gson.annotations.Expose;

public class SuccessfullResponse<T>{
    @Expose
    public T data;
}
