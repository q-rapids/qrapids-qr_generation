package qr.adapters.models;

import qr.models.Param;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ParameterServer {

    @SerializedName("id")
    @Expose
    private int id;

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

    @SerializedName("correctnessCondition")
    @Expose
    private String correctnessCondition;

    public Param getParams() {
        Param p = new Param();
        p.setName(this.name);
        p.setDescription(this.description);
        p.setType(metric.getType());
        return p;
    }

    public int getId() {
        return this.id;
    }

    public MetricServer getMetric() {
        return this.metric;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getCorrectnessCondition() {
        return this.correctnessCondition;
    }
}
