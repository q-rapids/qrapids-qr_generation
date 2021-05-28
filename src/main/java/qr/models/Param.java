package qr.models;

public class Param {

    private Integer id;
    private String name;
    private String description;
    private String correctnessCondition;
    private String type;
    private String value;
    private Integer metricId;
    private String metricName;

    public Param(Integer id, String name, String description, String correctnessCondition, String type, String value, Integer metricId, String metricName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.correctnessCondition = correctnessCondition;
        this.type = type;
        this.value = value;
        this.metricId = metricId;
        this.metricName = metricName;
    }

    public Param() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCorrectnessCondition() {
        return correctnessCondition;
    }

    public void setCorrectnessCondition(String correctnessCondition) {
        this.correctnessCondition = correctnessCondition;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getMetricId() {
        return metricId;
    }

    public void setMetricId(Integer metricId) {
        this.metricId = metricId;
    }

    public String getMetricName() {
        return metricName;
    }

    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }


    @Override
    public String toString() {
        return "Param{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
