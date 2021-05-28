package qr.adapters.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import qr.models.Metric;

import java.util.List;

public class MetricServer implements IServerModel{

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("minValue")
    @Expose
    private Float minValue;

    @SerializedName("maxValue")
    @Expose
    private Float maxValue;

    @SerializedName("possibleValues")
    @Expose
    private List<String> possibleValues;

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getType() {
        return this.type;
    }

    @Override
    public Metric toGenericModel() {
        Metric m = new Metric();
        m.setId(this.id);
        m.setName(this.name);
        m.setDescription(this.description);
        m.setType(this.type);
        if (this.type.equals("integer") || this.type.equals("float")) {
            m.setMinValue(this.minValue);
            m.setMaxValue(this.maxValue);
        }
        else if (this.type.equals("domain")) {
            m.setPossibleValues(this.possibleValues);
        }
        return m;
    }
}
