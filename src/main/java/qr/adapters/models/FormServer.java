package qr.adapters.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import qr.models.Form;

public class FormServer implements IServerModel {
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

    @SerializedName("fixedPart")
    @Expose
    private FixedPartServer fixedPart;

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

    public FixedPartServer getFixedPart() {
        return fixedPart;
    }

    public void setFixedPart(FixedPartServer fixedPart) {
        this.fixedPart = fixedPart;
    }

    @Override
    public Form toGenericModel() {
        return new Form(name,description,comments,fixedPart.toGenericModel());
    }
}
