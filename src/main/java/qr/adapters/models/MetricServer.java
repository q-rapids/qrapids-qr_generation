package qr.adapters.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MetricServer {

    @SerializedName("type")
    @Expose
    private String type;

    public String getType() {
        return this.type;
    }
}
