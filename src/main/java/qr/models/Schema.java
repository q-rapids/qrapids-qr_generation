package qr.models;

import java.util.List;

public class Schema {
    private Integer id;
    private String name;
    private List<Classifier> rootClassifiers;

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

    public List<Classifier> getRootClassifiers() {
        return rootClassifiers;
    }

    public void setRootClassifiers(List<Classifier> rootClassifiers) {
        this.rootClassifiers = rootClassifiers;
    }
}
