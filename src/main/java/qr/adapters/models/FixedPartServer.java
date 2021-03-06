package qr.adapters.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import qr.models.FixedPart;
import qr.models.Param;

import java.util.ArrayList;
import java.util.List;

public class FixedPartServer implements IServerModel {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("formText")
    @Expose
    private String formText;

    @SerializedName("parameters")
    @Expose
    private List<ParameterServer> parameters;

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

    public List<ParameterServer> getParameters() {
        return parameters;
    }

    public void setParameters(List<ParameterServer> parameters) {
        this.parameters = parameters;
    }

    @Override
    public FixedPart toGenericModel() {
        List<Param> paramList = new ArrayList<>();
        if (parameters != null) {
            for (ParameterServer parameter : parameters) {
                paramList.add(parameter.getParams());
            }
        }
        return new FixedPart(formText, paramList);
    }
}
