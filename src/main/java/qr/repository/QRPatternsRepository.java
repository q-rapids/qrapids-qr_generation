package qr.repository;

import qr.adapters.IRequirementPatternAdapter;
import qr.adapters.RequirementPatternAdapterImpl;
import qr.adapters.remote.ApiUtils;
import qr.adapters.remote.SOServices;
import qr.models.Classifier;
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

    public Classifier getRootClassifier() {
        Schema schema = ir.getSchemaByName("Schema Q-rapids");
        return schema.getRootClassifiers().get(0); //Supposing that there is only one root classifier
    }

    public boolean importCatalogue(String json){
        return ir.importCatalogue(json);
    }

    public int createQRPattern(QualityRequirementPattern newPattern) {
        return ir.createRequirementPattern(newPattern);
    }

    public void updateQRPattern(long id, QualityRequirementPattern editedPattern) {
        ir.updateRequirementPattern(id, editedPattern);
    }

    public void deleteQRPattern(long id) {
        ir.deleteRequirementPattern(id);
    }

    public void updateClassifier(Integer id, String name, Integer pos, List<Integer> patternsList) {
        Schema schema = ir.getSchemaByName("Schema Q-rapids");
        ir.updateClassifier(schema.getId(), id, name, pos, patternsList);
    }
}
