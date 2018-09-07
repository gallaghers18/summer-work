package network;


import com.google.gson.JsonObject;


import data.Discover;
import retrofit2.Call;
import retrofit2.http.*;

public interface APIService {

    @GET("movie")
    Call<Discover> getDiscover(@Query("api_key") String api_key,
                               @Query("language") String language,
                               @Query("sort_by") String sort_by,
                               @Query("include_adult") boolean include_adult,
                               @Query("include_video") boolean include_video,
                               @Query("page") int page);

}

