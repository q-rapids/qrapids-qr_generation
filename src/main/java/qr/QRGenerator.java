package qr;

import qr.models.Alert;
import qr.models.Classifier;
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
}
