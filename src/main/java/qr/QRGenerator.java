package qr;

import qr.models.Alert;
import qr.models.Classifier;
import qr.models.Metric;
import qr.repository.QRPatternsRepository;
import qr.models.QualityRequirementPattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QRGenerator {

    private QRPatternsRepository qrPatternsRepository;
    private List<QualityRequirementPattern> QRPlist;

    public QRGenerator (String urlBase) {
        qrPatternsRepository = new QRPatternsRepository(urlBase);
    }

    public boolean existsQRPattern(Alert alert){
        QRPlist = qrPatternsRepository.getQRPatterns(alert.getCategory());
        return !QRPlist.isEmpty();
    }

    public List<QualityRequirementPattern> generateQRs(Alert alert){
        QRPlist = qrPatternsRepository.getQRPatterns(alert.getCategory());
        return QRPlist;
    }

    public List<QualityRequirementPattern> getAllQRPatterns () {
        return qrPatternsRepository.getAllQRPatterns();
    }

    public QualityRequirementPattern getQRPattern(long id) {
        return qrPatternsRepository.getQRPattern(id);
    }

    public Map<Integer, String> getMetricsForPatterns(List<Integer> ids) {
        Map<Integer, String> metricForPatternMap = new HashMap<>();
        for (Integer id : ids) {
            metricForPatternMap.put(id, qrPatternsRepository.getMetricForPattern(id));
        }
        return metricForPatternMap;
    }

    /**
     * Create a new quality requirement pattern.
     * @param newPattern Pattern that will be created
     * @return New pattern identifier (if value is -1, error while creating pattern)
     */
    public int createQRPattern(QualityRequirementPattern newPattern) {
        return qrPatternsRepository.createQRPattern(newPattern);
    }

    /**
     * Edit a quality requirement pattern.
     * @param id Pattern identifier
     * @param editedPattern Edited pattern
     * @return Pattern edited successfully
     */
    public boolean updateQRPattern(long id, QualityRequirementPattern editedPattern) {
        return qrPatternsRepository.updateQRPattern(id, editedPattern);
    }

    /**
     * Delete a quality requirement pattern.
     * @param id Pattern identifier
     */
    public void deleteQRPattern(long id) {
        qrPatternsRepository.deleteQRPattern(id);
    }

    /**
     * Get all the first level classifiers.
     * @return List containing all the classifiers
     */
    public List<Classifier> getAllClassifiers() {
        Classifier rootClassifier = qrPatternsRepository.getRootClassifier();
        return rootClassifier.getInternalClassifiers();
    }

    /**
     * Get one classifier.
     * @param id Classifier identifier
     * @return Classifier with the given identifier
     */
    public Classifier getClassifier(long id) {
        return qrPatternsRepository.getClassifier(id);
    }

    /**
     * Create a new classifier.
     * @param name New classifier name
     * @param parentId Identifier of the classifier that will be the parent
     */
    public void createClassifier(String name, long parentId) {
        qrPatternsRepository.createClassifier(name, parentId);
    }

    /**
     * Edit a classifier that only contains patterns. The list of contained patterns is replaced.
     * @param id Classifier identifier
     * @param name Edited name of classifier
     * @param pos Classifier position
     * @param patternsList New pattern identifiers list of classifier
     */
    public void updateClassifierWithPatterns(long id, String name, Integer pos, List<Integer> patternsList) {
        qrPatternsRepository.updateClassifierWithPatterns(id, name, pos, patternsList);
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
        qrPatternsRepository.updateAndMoveClassifier(id, name, oldParentId, newParentId);
    }

    /**
     * Delete a classifier.
     * @param id Classifier identifier
     */
    public void deleteClassifier(long id) {
        qrPatternsRepository.deleteClassifier(id);
    }

    /**
     * Get all the metrics.
     * @return List containing all the metrics
     */
    public List<Metric> getAllMetrics() {
        return qrPatternsRepository.getAllMetrics();
    }

    /**
     * Get one metric.
     * @param id Metric identifier
     * @return Metric with the given identifier
     */
    public Metric getMetric(long id) {
        return qrPatternsRepository.getMetric(id);
    }

    /**
     * Create a new metric.
     * @param metric Metric that will be created
     * @return Metric created successfully
     */
    public boolean createMetric(Metric metric) {
        return qrPatternsRepository.createMetric(metric);
    }

    /**
     * Edit a metric.
     * @param id Metric identifier
     * @param metric Edited metric
     * @return Metric edited successfully
     */
    public boolean updateMetric(long id, Metric metric) {
        return qrPatternsRepository.updateMetric(id, metric);
    }

    /**
     * Delete a metric.
     * @param id Metric identifier
     * @return Metric deleted successfully
     */
    public boolean deleteMetric(long id) {
        return qrPatternsRepository.deleteMetric(id);
    }
}
