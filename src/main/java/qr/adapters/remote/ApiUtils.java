package qr.adapters.remote;

/**
 * Created by Awais Iqbal on 24/10/2017.
 */

public class ApiUtils {

    public static SOServices getSOService(String urlBase) {
        return RetrofitClient.getClient(urlBase + "/api/").create(SOServices.class);
    }
}
