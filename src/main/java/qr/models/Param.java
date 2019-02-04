package qr.models;

public class Param {

    private String name;
    private String description;
    private String type;
    private String value;

    public Param(String name, String description, String type, String value) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.value = value;
    }

    public Param() {

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
