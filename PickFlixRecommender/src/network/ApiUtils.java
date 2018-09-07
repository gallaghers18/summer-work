package network;

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "https://api.themoviedb.org/3/discover/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
