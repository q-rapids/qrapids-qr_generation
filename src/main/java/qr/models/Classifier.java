package qr.models;

import java.util.List;

public class Classifier {
    private Integer id;
    private String name;
    private List<Classifier> internalClassifiers;
    private List<QualityRequirementPattern> requirementPatterns;

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

    public List<Classifier> getInternalClassifiers() {
        return internalClassifiers;
    }

    public void setInternalClassifiers(List<Classifier> internalClassifiers) {
        this.internalClassifiers = internalClassifiers;
    }

    public List<QualityRequirementPattern> getRequirementPatterns() {
        return requirementPatterns;
    }

    public void setRequirementPatterns(List<QualityRequirementPattern> requirementPatterns) {
        this.requirementPatterns = requirementPatterns;
    }

    public boolean hasRequirementPattern (Integer id) {
        boolean found = false;
        for (int i = 0; i < requirementPatterns.size() && !found; i++) {
            found = requirementPatterns.get(i).getId().equals(id);
        }
        return found;
    }
}
