package qr.adapters.models;

import qr.models.Param;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PatternItemServer {

    @SerializedName("parameters")
    @Expose
    private List<ParameterServer> parameters;

    public List<Param> getParams() {
        List<Param> params = new ArrayList<>();
        for (int i = 0; i < parameters.size(); i++) {
            params.add(parameters.get(i).getParams());
        }
        return params;
    }
}
