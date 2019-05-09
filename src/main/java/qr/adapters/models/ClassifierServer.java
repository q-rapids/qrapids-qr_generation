package qr.adapters.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import qr.models.Classifier;
import qr.models.QualityRequirementPattern;

import java.util.ArrayList;
import java.util.List;

public class ClassifierServer implements IServerModel{

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("internalClassifiers")
    @Expose
    private List<ClassifierServer> internalClassifiersServer;

    @SerializedName("requirementPatterns")
    @Expose
    private List<QRPatternServer> requirementPatternsServer;

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

    public List<ClassifierServer> getInternalClassifiersServer() {
        return internalClassifiersServer;
    }

    public void setInternalClassifiersServer(List<ClassifierServer> internalClassifiersServer) {
        this.internalClassifiersServer = internalClassifiersServer;
    }

    public List<QRPatternServer> getRequirementPatternsServer() {
        return requirementPatternsServer;
    }

    public void setRequirementPatternsServer(List<QRPatternServer> requirementPatternsServer) {
        this.requirementPatternsServer = requirementPatternsServer;
    }

    @Override
    public Classifier toGenericModel() {
        Classifier classifier = new Classifier();
        classifier.setId(this.id);
        classifier.setName(this.name);
        List<Classifier> internalClassifiers = new ArrayList<>();
        if (this.internalClassifiersServer != null) {
            for (ClassifierServer classifierServer : internalClassifiersServer) {
                internalClassifiers.add(classifierServer.toGenericModel());
            }
        }
        classifier.setInternalClassifiers(internalClassifiers);
        List<QualityRequirementPattern> requirementPatterns = new ArrayList<>();
        if (this.requirementPatternsServer != null) {
            for (QRPatternServer qrPatternServer : requirementPatternsServer) {
                requirementPatterns.add(qrPatternServer.toGenericModel());
            }
        }
        classifier.setRequirementPatterns(requirementPatterns);
        return classifier;
    }
}
