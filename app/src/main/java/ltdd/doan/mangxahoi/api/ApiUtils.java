package ltdd.doan.mangxahoi.api;

public class ApiUtils {
    public static final String BASE_URL = "";

    public static ApiInterface getApiService() {
        return ApiClient.getClient(BASE_URL).create(ApiInterface.class);
    }
}
