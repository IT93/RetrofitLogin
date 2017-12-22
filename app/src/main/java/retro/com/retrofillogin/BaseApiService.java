package retro.com.retrofillogin;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by WeltesDev on 12/22/2017.
 */

public interface BaseApiService {
    @FormUrlEncoded
    @POST("login.php")
    Call<List<Item>> loginRequest(
            @Field("email") String email,
            @Field("password") String password);

}
