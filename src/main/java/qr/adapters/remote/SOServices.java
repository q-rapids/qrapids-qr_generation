package qr.adapters.remote;

import com.google.gson.JsonObject;
import qr.adapters.models.ClassifierServer;
import qr.adapters.models.ClassifierServerEdit;
import qr.adapters.models.Forms;
import qr.adapters.models.MetricServer;
import qr.adapters.models.QRPatternServer;
import qr.adapters.models.QRPatternServerEdit;
import qr.adapters.models.SchemaServer;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * Created by Awais Iqbal on 24/10/2017.
 */

public interface SOServices {


    //MEETINGS
    @GET("patterns")
    Call<List<QRPatternServer>> getAllPatterns(@Query("complete") boolean complete);

    @GET("patterns/{id}")
    Call<QRPatternServer> getAllPatterns(@Path("id") long id);

    @GET("patterns")
    Call<List<QRPatternServer>> getPatternsOfGivenClassifier(@Query("namesList") List<String> name, @Query("complete") boolean complete, @Query("completeClassifiers") boolean completeClassifiers);

    @Headers("Content-Type: application/json")
    @POST("catalogue")
    Call<Void> importCatalogue(@Body JsonObject json);

    @GET("schemas")
    Call<List<SchemaServer>> getSchemaByName(@Query("names") List<String> names);

    @Headers("Content-Type: application/json")
    @POST("patterns")
    Call<JsonObject> createPattern(@Body QRPatternServerEdit.PatternEdit pattern);

    @Headers("Content-Type: application/json")
    @PUT("patterns/{id}")
    Call<Void> updatePattern(@Path("id") long id, @Body QRPatternServerEdit.PatternEdit pattern);

    @DELETE("patterns/{id}")
    Call<Void> deletePattern(@Path("id") long id);

    @Headers("Content-Type: application/json")
    @PUT("schemas/{sid}/classifiers/{cid}")
    Call<Void> updateClassifier(@Path("sid") long schemaId, @Path("cid") long classifierId, @Body ClassifierServerEdit classifier);

    @GET("schemas/{sid}/classifiers/{cid}")
    Call<ClassifierServer> getClassifier(@Path("sid") long schemaId, @Path("cid") long classifierId);

    @GET("metrics")
    Call<List<MetricServer>> getAllMetrics();

    @GET("metrics/{id}")
    Call<MetricServer> getMetric(@Path("id") long id);

    @Headers("Content-Type: application/json")
    @POST("metrics")
    Call<Void> createMetric(@Query("type") String type, @Body MetricServer metric);


}
