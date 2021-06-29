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

    /**
     * Create a new quality requirement pattern.
     * @param newPattern Pattern that will be created
     * @return New pattern identifier (if value is -1, error while creating pattern)
     */
    public int createRequirementPattern(QualityRequirementPattern newPattern);

    /**
     * Edit a quality requirement pattern.
     * @param id Pattern identifier
     * @param editedPattern Edited pattern
     * @return Pattern edited successfully
     */
    public boolean updateRequirementPattern(long id, QualityRequirementPattern editedPattern);

    /**
     * Delete a quality requirement pattern.
     * @param id Pattern identifier
     */
    public void deleteRequirementPattern(long id);

    /**
     * Get a classifier by its identifier.
     * @param schemaId Identifier of schema that contains the classifier
     * @param id Classifier identifier
     * @return Classifier with the given identifier
     */
    public Classifier getClassifierById(long schemaId, long id);

    /**
     * Create a new classifier.
     * @param schemaId Identifier of schema where the classifier will be created
     * @param name New classifier name
     * @param parentId Identifier of the classifier that will be the parent
     */
    public void createClassifier(long schemaId, String name, long parentId);

    /**
     * Edit a classifier that only contains patterns.
     * @param schemaId Identifier of schema that contains the classifier
     * @param id Classifier identifier
     * @param name Edited name of classifier
     * @param pos Classifier position
     * @param patternsList New pattern identifiers list of classifier
     */
    public void updateClassifierWithPatterns(long schemaId, long id, String name, Integer pos, List<Integer> patternsList);

    /**
     * Edit a classifier that contains other classifiers or patterns.
     * @param schemaId Identifier of schema that contains the classifier
     * @param id Classifier identifier
     * @param name Edited name of classifier
     */
    public void updateClassifier(long schemaId, long id, String name);

    /**
     * Edit and move a classifier that contains other classifiers or patterns.
     * @param schemaId Identifier of schema that contains the classifier
     * @param id Classifier identifier
     * @param name Edited name of classifier
     * @param oldParentId Old parent classifier identifier
     * @param newParentId New parent classifier identifier
     */
    public void updateAndMoveClassifier(long schemaId, long id, String name, long oldParentId, long newParentId);

    /**
     * Delete a classifier.
     * @param schemaId Identifier of schema that contains the classifier
     * @param id Classifier identifier
     * @param parentId Parent classifier identifier
     */
    public void deleteClassifier(long schemaId, long id, long parentId);

    /**
     * Get all the metrics.
     * @return List containing all the metrics
     */
    public List<Metric> getAllMetrics();

    /**
     * Get a metric by its identifier
     * @param id Metric identifier
     * @return Metric with the given identifier
     */
    public Metric getMetricById(long id);

    /**
     * Create a new metric
     * @param newMetric Metric that will be created
     * @return Metric created successfully
     */
    public boolean createMetric(Metric newMetric);

    /**
     * Edit a metric.
     * @param id Metric identifier
     * @param metric Edited metric
     * @return Metric edited successfully
     */
    public boolean updateMetric(long id, Metric metric);

    /**
     * Delete a metric.
     * @param id Metric identifier
     * @return Metric edited successfully
     */
    public boolean deleteMetric(long id);
}
