package retro.com.retrofillogin;

/**
 * Created by WeltesDev on 12/22/2017.
 */

public class UtilsApi {
    public static final String BASE_URL_API = "http://10.0.2.2:7777/login/";

    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}
