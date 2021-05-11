package qr.adapters.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ClassifierServerEdit {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("comments")
    @Expose
    private String comments;

    @SerializedName("pos")
    @Expose
    private Integer pos;

    @SerializedName("internalClassifiers")
    @Expose
    private List<ClassifierServerEdit> internalClassifiers;

    @SerializedName("requirementPatterns")
    @Expose
    private List<Integer> requirementPatternsId;

    public ClassifierServerEdit(String name, Integer pos) {
        this.name = name;
        this.description = "";
        this.comments = "";
        this.pos = pos;
        this.internalClassifiers = null;
        this.requirementPatternsId = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPos() {
        return pos;
    }

    public void setPos(Integer pos) {
        this.pos = pos;
    }

    public void setInternalClassifiers(List<ClassifierServerEdit> internalClassifiers) {
        this.internalClassifiers = internalClassifiers;
    }

    public void setRequirementPatternsId(List<Integer> requirementPatternsId) {
        this.requirementPatternsId = requirementPatternsId;
    }

}
