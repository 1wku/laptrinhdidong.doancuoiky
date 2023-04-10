package ltdd.doan.mangxahoi.api;

public class ApiUtils {
    public static final String BASE_URL = "http://127.0.0.1:3000/";

    public static ApiInterface getApiService() {
        return ApiClient.getClient(BASE_URL).create(ApiInterface.class);
    }
}
