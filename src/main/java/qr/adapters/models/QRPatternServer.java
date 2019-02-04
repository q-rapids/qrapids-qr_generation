package qr.adapters.models;

import qr.models.Form;
import qr.models.QualityRequirementPattern;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class QRPatternServer implements IServerModel {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("comments")
    @Expose
    private String comments;

    @SerializedName("author")
    @Expose
    private String author;

    @SerializedName("available")
    @Expose
    private String available;

    @SerializedName("goal")
    @Expose
    private String goal;

    @SerializedName("forms")
    @Expose
    private List<FormServer> forms;

    @SerializedName("costFunction")
    @Expose
    private List<Map<String, String>> costFunction;
    /*
        Example of costFunction:
        costFunction: [{
            "name":"bla",
            "function":"funct1"
        },{
            "name":"bla2",
            "function":"funct2"
        }]
     */


    /**
     * No args constructor for use in serialization
     */
    public QRPatternServer() {
    }


    public QualityRequirementPattern toGenericModel() {

        QualityRequirementPattern qrp = new QualityRequirementPattern();
        qrp.setId(this.id);
        qrp.setName(this.name);
        qrp.setComments(this.comments);
        qrp.setDescription(this.description);
        qrp.setGoal(this.goal);
        List<Form> formList = new ArrayList<>();
        if (forms != null) {
            for (int i = 0; i < forms.size(); i++) {
                formList.add(forms.get(i).toGenericModel());
            }
        }
        qrp.setForms(formList);
        if (this.costFunction != null && !this.costFunction.isEmpty()) {
            qrp.setCostFunction(this.costFunction.get(0).get("function"));//TODO pending to change when we can have more then 1 costFunction
        }
        return qrp;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public List<FormServer> getForms() {
        return forms;
    }

    public void setForms(List<FormServer> forms) {
        this.forms = forms;
    }

    public List<Map<String, String>> getCostFunction() {
        return costFunction;
    }

    public void setCostFunction(List<Map<String, String>> costFunction) {
        this.costFunction = costFunction;
    }
}
