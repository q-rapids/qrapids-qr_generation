package qr.adapters.models;

import qr.models.Param;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ParamsServer implements IServerModel {

    @SerializedName("fixedPart")
    @Expose
    private PatternItemServer fixedPart;

    @SerializedName("extendedParts")
    @Expose
    private List<PatternItemServer> extendedParts;


    @Override
    public List<Param> toGenericModel() {
        List<Param> lp = new ArrayList<>();
        lp.addAll(fixedPart.getParams());
        for (int i = 0; i < extendedParts.size(); i++) {
            lp.addAll(extendedParts.get(i).getParams());
        }
        return lp;
    }

}
