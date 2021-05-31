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

    @SerializedName("comments")
    @Expose
    private String comments;

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

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getType() {
        return this.type;
    }

    public Float getMinValue() {
        return this.minValue;
    }

    public void setMinValue(Float minValue) {
        this.minValue = minValue;
    }

    public Float getMaxValue() {
        return this.maxValue;
    }

    public void setMaxValue(Float maxValue) {
        this.maxValue = maxValue;
    }

    public List<String> getPossibleValues() {
        return this.possibleValues;
    }

    public void setPossibleValues(List<String> possibleValues) {
        this.possibleValues = possibleValues;
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
