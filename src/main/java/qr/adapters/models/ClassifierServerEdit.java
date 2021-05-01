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
    private List<Integer> internalClassifiersId;

    @SerializedName("requirementPatterns")
    @Expose
    private List<Integer> requirementPatternsId;

    public ClassifierServerEdit(String name, Integer pos, List<Integer> requirementPatternsId) {
        this.name = name;
        this.description = "";
        this.comments = "";
        this.pos = pos;
        this.internalClassifiersId = null;
        this.requirementPatternsId = requirementPatternsId;
    }

}
