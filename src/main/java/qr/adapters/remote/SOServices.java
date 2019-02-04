package qr.adapters.remote;

import com.google.gson.JsonObject;
import qr.adapters.models.Forms;
import qr.adapters.models.QRPatternServer;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * Created by Awais Iqbal on 24/10/2017.
 */

public interface SOServices {


    //MEETINGS
    @GET("patterns")
    Call<List<QRPatternServer>> getAllPatterns();

    @GET("patterns/{id}")
    Call<QRPatternServer> getAllPatterns(@Path("id") long id);

    @GET("patterns")
    Call<List<QRPatternServer>> getPatternsOfGivenClassifier(@Query("namesList") List<String> name, @Query("complete") boolean complete);

    @Headers("Content-Type: application/json")
    @POST("catalogue")
    Call<Void> importCatalogue(@Body JsonObject json);


}
