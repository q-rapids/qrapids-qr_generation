package qr.models;

import java.util.List;

public class QualityRequirementPattern {
    private Integer id;
    private String name;
    private String comments;
    private String description;
    private String goal;
    private List<Form> forms;
    private String costFunction;

    public QualityRequirementPattern() {
    }

    public QualityRequirementPattern(Integer id, String name, String comments, String description, String goal, List<Form> forms, String costFunction) {
        this.id = id;
        this.name = name;
        this.comments = comments;
        this.description = description;
        this.goal = goal;
        this.forms = forms;
        this.costFunction = costFunction;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public List<Form> getForms() {
        return forms;
    }

    public void setForms(List<Form> forms) {
        this.forms = forms;
    }

    public String getCostFunction() {
        return costFunction;
    }

    public void setCostFunction(String costFunction) {
        this.costFunction = costFunction;
    }

    @Override
    public String
    toString() {
        return "QualityRequirementPattern{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", comments='" + comments + '\'' +
                ", description='" + description + '\'' +
                ", goal='" + goal + '\'' +
                ", forms=" + forms +
                ", costFunction='" + costFunction + '\'' +
                '}';
    }
}
