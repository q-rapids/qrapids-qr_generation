package qr.adapters.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import qr.models.FixedPart;

public class FixedPartServer implements IServerModel {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("formText")
    @Expose
    private String formText;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFormText() {
        return formText;
    }

    public void setFormText(String formText) {
        this.formText = formText;
    }

    @Override
    public FixedPart toGenericModel() {
        return new FixedPart(formText);
    }
}