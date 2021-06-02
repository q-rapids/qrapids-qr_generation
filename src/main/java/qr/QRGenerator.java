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

    public List<Classifier> getAllClassifiers() {
        Classifier rootClassifier = qrPatternsRepository.getRootClassifier();
        return rootClassifier.getInternalClassifiers();
    }

    public int createQRPattern(QualityRequirementPattern newPattern) {
        return qrPatternsRepository.createQRPattern(newPattern);
    }

    public void updateQRPattern(long id, QualityRequirementPattern editedPattern) {
        qrPatternsRepository.updateQRPattern(id, editedPattern);
    }

    public void deleteQRPattern(long id) {
        qrPatternsRepository.deleteQRPattern(id);
    }

    public Classifier getClassifier(long id) {
        return qrPatternsRepository.getClassifier(id);
    }

    public void createClassifier(String name, long parentId) {
        qrPatternsRepository.createClassifier(name, parentId);
    }

    //Update a classifier that only contains patterns
    //The list of patterns is replaced
    public void updateClassifierWithPatterns(long id, String name, Integer pos, List<Integer> patternsList) {
        qrPatternsRepository.updateClassifierWithPatterns(id, name, pos, patternsList);
    }

    //Update (and move, if oldParentId != newParentId) a classifier that contains other classifiers or patterns
    //The classifier keeps all its patterns or child classifiers
    public void updateAndMoveClassifier(long id, String name, long oldParentId, long newParentId) {
        qrPatternsRepository.updateAndMoveClassifier(id, name, oldParentId, newParentId);
    }

    public void deleteClassifier(long id) {
        qrPatternsRepository.deleteClassifier(id);
    }

    public List<Metric> getAllMetrics() {
        return qrPatternsRepository.getAllMetrics();
    }

    public Metric getMetric(long id) {
        return qrPatternsRepository.getMetric(id);
    }

    public boolean createMetric(Metric metric) {
        return qrPatternsRepository.createMetric(metric);
    }

    public boolean updateMetric(long id, Metric metric) {
        return qrPatternsRepository.updateMetric(id, metric);
    }

    public boolean deleteMetric(long id) {
        return qrPatternsRepository.deleteMetric(id);
    }
}
