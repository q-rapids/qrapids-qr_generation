package qr.repository;

import qr.adapters.IRequirementPatternAdapter;
import qr.adapters.RequirementPatternAdapterImpl;
import qr.adapters.remote.ApiUtils;
import qr.adapters.remote.SOServices;
import qr.models.Param;
import qr.models.QualityRequirementPattern;

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

    public boolean importCatalogue(String json){
        return ir.importCatalogue(json);
    }

}
