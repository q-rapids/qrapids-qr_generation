package qr.adapters.models;

import qr.models.Param;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ParameterServer {

    @SerializedName("metric")
    @Expose
    private MetricServer metric;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("value")
    @Expose
    private String value;

    public Param getParams() {
        Param p = new Param();
        p.setName(this.name);
        p.setDescription(this.description);
        p.setType(metric.getType());
        return p;
    }
}
