package qr.adapters;

import qr.models.Classifier;
import qr.models.Metric;
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

    public int createRequirementPattern(QualityRequirementPattern newPattern);

    public void updateRequirementPattern(long id, QualityRequirementPattern editedPattern);

    public void deleteRequirementPattern(long id);

    public Classifier getClassifierById(long schemaId, long id);

    public void createClassifier(long schemaId, String name, long parentId);

    public void updateClassifierWithPatterns(long schemaId, long id, String name, Integer pos, List<Integer> patternsList);

    public void updateClassifier(long schemaId, long id, String name);

    public void updateAndMoveClassifier(long schemaId, long id, String name, long oldParentId, long newParentId);

    public void deleteClassifier(long schemaId, long id, long parentId);

    public List<Metric> getAllMetrics();

    public Metric getMetricById(long id);

    public boolean createMetric(Metric newMetric);

    public boolean updateMetric(long id, Metric metric);
}
