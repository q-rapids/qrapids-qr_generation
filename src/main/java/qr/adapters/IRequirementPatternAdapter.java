package qr.adapters;

import qr.models.Classifier;
import qr.models.QualityRequirementPattern;
import qr.models.Schema;

import java.util.List;

/**
 * Interface to operate over @{@link QualityRequirementPattern}
 */
public interface IRequirementPatternAdapter {
    public List<QualityRequirementPattern> getAllRequirementPatterns();

    public QualityRequirementPattern getRequirementPatternByID(long id);

    public List<QualityRequirementPattern> getPatternsOfGivenClassifier(String name);

    public boolean importCatalogue(String json);

    public Schema getSchemaByName(String name);

    public Classifier getClassifierById(long schemaId, long id);
}
