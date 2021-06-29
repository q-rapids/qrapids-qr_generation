package qr.repository;

import qr.adapters.IRequirementPatternAdapter;
import qr.adapters.RequirementPatternAdapterImpl;
import qr.adapters.remote.ApiUtils;
import qr.adapters.remote.SOServices;
import qr.models.Classifier;
import qr.models.Metric;
import qr.models.Param;
import qr.models.QualityRequirementPattern;
import qr.models.Schema;

import java.util.ArrayList;
import java.util.List;

public class QRPatternsRepository {

    private List<QualityRequirementPattern> QRPlist = new ArrayList<>();
    private IRequirementPatternAdapter ir;
    private SOServices mServices;


    public QRPatternsRepository(String urlBase) {
        mServices = ApiUtils.getSOService(urlBase);
        ir = new RequirementPatternAdapterImpl(mServices);
    }

    public List<QualityRequirementPattern> getQRPatterns(String id) {
        return ir.getPatternsOfGivenClassifier(id); //Call to the Interface to get the patterns related to the alert
    }

    public List<QualityRequirementPattern> getAllQRPatterns () {
        return ir.getAllRequirementPatterns();
    }

    public QualityRequirementPattern getQRPattern(long id) {
        return ir.getRequirementPatternByID(id);
    }

    public String getMetricForPattern(Integer id) {
        List<String> metrics = new ArrayList<>();
        Schema schema = ir.getSchemaByName("Schema Q-rapids");
        for (Classifier rootClassifier : schema.getRootClassifiers()) {
            for (Classifier internalClassifier1 : rootClassifier.getInternalClassifiers()) {
                for (Classifier internalClassifier2 : internalClassifier1.getInternalClassifiers()) {
                    if (internalClassifier2.hasRequirementPattern(id)) {
                        metrics.add(internalClassifier2.getName());
                    }
                }
            }
        }
        return metrics.get(0);
    }

    /**
     * Create a new quality requirement pattern.
     * @param newPattern Pattern that will be created
     * @return New pattern identifier (if value is -1, error while creating pattern)
     */
    public int createQRPattern(QualityRequirementPattern newPattern) {
        return ir.createRequirementPattern(newPattern);
    }

    /**
     * Edit a quality requirement pattern.
     * @param id Pattern identifier
     * @param editedPattern Edited pattern
     * @return Pattern edited successfully
     */
    public boolean updateQRPattern(long id, QualityRequirementPattern editedPattern) {
        return ir.updateRequirementPattern(id, editedPattern);
    }

    /**
     * Delete a quality requirement pattern.
     * @param id Pattern identifier
     */
    public void deleteQRPattern(long id) {
        ir.deleteRequirementPattern(id);
    }

    /**
     * Get the root classifier of the catalogue.
     * @return Root classifier
     */
    public Classifier getRootClassifier() {
        Schema schema = ir.getSchemaByName("Schema Q-rapids");
        return schema.getRootClassifiers().get(0); //Supposing that there is only one root classifier
    }

    /**
     * Get one classifier by its identifier.
     * @param id Classifier identifier
     * @return Classifier with the given identifier
     */
    public Classifier getClassifier(long id) {
        Schema schema = ir.getSchemaByName("Schema Q-rapids");
        return ir.getClassifierById(schema.getId(), id);
    }

    /**
     * Create a new classifier.
     * @param name New classifier name
     * @param parentId Identifier of the classifier that will be the parent
     */
    public void createClassifier(String name, long parentId) {
        Schema schema = ir.getSchemaByName("Schema Q-rapids");
        long parent = parentId;
        if (parent == -1) { //-1 means that parent is root classifier
            parent = schema.getRootClassifiers().get(0).getId();
        }
        ir.createClassifier(schema.getId(), name, parent);
    }

    /**
     * Edit a classifier that only contains patterns.
     * @param id Classifier identifier
     * @param name Edited name of classifier
     * @param pos Classifier position
     * @param patternsList New pattern identifiers list of classifier
     */
    public void updateClassifierWithPatterns(long id, String name, Integer pos, List<Integer> patternsList) {
        Schema schema = ir.getSchemaByName("Schema Q-rapids");
        ir.updateClassifierWithPatterns(schema.getId(), id, name, pos, patternsList);
    }

    /**
     * Edit (and move, if oldParentId != newParentId) a classifier that contains other classifiers or patterns.
     * The classifier keeps all its patterns or child classifiers.
     * @param id Classifier identifier
     * @param name Edited name of classifier
     * @param oldParentId Old parent classifier identifier (-1 means root classifier)
     * @param newParentId New parent classifier identifier (-1 means root classifier)
     */
    public void updateAndMoveClassifier(long id, String name, long oldParentId, long newParentId) {
        //As PABRE-WS only allows moving a classifier deleting it from its old parent and recreating it from its new
        //parent, we have one method to only edit the classifier and another to edit and move it.
        Schema schema = ir.getSchemaByName("Schema Q-rapids");

        if (oldParentId == newParentId) {
            //Edit the pattern without moving
            ir.updateClassifier(schema.getId(), id, name);
        } else {
            long oldParent = oldParentId;
            if (oldParent == -1) {
                oldParent = schema.getRootClassifiers().get(0).getId();
            }
            long newParent = newParentId;
            if (newParent == -1) {
                newParent = schema.getRootClassifiers().get(0).getId();
            }
            //Edit and move the pattern
            ir.updateAndMoveClassifier(schema.getId(), id, name, oldParent, newParent);
        }
    }

    /**
     * Delete a classifier.
     * @param id Classifier identifier
     */
    public void deleteClassifier(long id) {
        Schema schema = ir.getSchemaByName("Schema Q-rapids");
        long parent = 0;
        for (Classifier classifier : schema.getRootClassifiers().get(0).getInternalClassifiers()) {
            if (classifier.getId() == id) {
                parent = schema.getRootClassifiers().get(0).getId();
            } else {
                for (Classifier c2 : classifier.getInternalClassifiers()) {
                    if (c2.getId() == id) {
                        parent = classifier.getId();
                        break;
                    }
                }
            }
        }
        ir.deleteClassifier(schema.getId(), id, parent);
    }

    /**
     * Get all the metrics.
     * @return List containing all the metrics
     */
    public List<Metric> getAllMetrics() {
        return ir.getAllMetrics();
    }

    /**
     * Get one metric.
     * @param id Metric identifier
     * @return Metric with the given identifier
     */
    public Metric getMetric(long id) {
        return ir.getMetricById(id);
    }

    /**
     * Create a new metric.
     * @param metric Metric that will be created
     * @return Metric created successfully
     */
    public boolean createMetric(Metric metric) {
        return ir.createMetric(metric);
    }

    /**
     * Edit a metric.
     * @param id Metric identifier
     * @param metric Edited metric
     * @return Metric edited successfully
     */
    public boolean updateMetric(long id, Metric metric) {
        return ir.updateMetric(id, metric);
    }

    /**
     * Delete a metric.
     * @param id Metric identifier
     * @return Metric edited successfully
     */
    public boolean deleteMetric(long id) {
        return ir.deleteMetric(id);
    }

    public boolean importCatalogue(String json){
        return ir.importCatalogue(json);
    }

}
