package qr.adapters.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MetricServer {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("type")
    @Expose
    private String type;

    public int getId() {
        return this.id;
    }

    public String getType() {
        return this.type;
    }
}
