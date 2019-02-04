package qr.models;

import qr.models.enumerations.Type;

import java.util.List;

public class Alert {

    private String id_element;
    private String name;
    private Type type;
    private float value;
    private float threshold;
    private String category;
    private List<Object> artefacts;

    public Alert(String id_element, String name, Type type, float value, float threshold, String category, List<Object> artefacts) {
        this.id_element = id_element;
        this.name = name;
        this.type = type;
        this.value = value;
        this.threshold = threshold;
        this.category = category;
        this.artefacts = artefacts;
    }

    public String getId_element() {
        return id_element;
    }

    public void setId_element(String id_element) {
        this.id_element = id_element;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getThreshold() {
        return threshold;
    }

    public void setThreshold(float threshold) {
        this.threshold = threshold;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Object> getArtefacts() {
        return artefacts;
    }

    public void setArtefacts(List<Object> artefacts) {
        this.artefacts = artefacts;
    }
}
