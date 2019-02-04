package qr.adapters;

import com.google.gson.Gson;
import qr.adapters.models.QRPatternServer;
import qr.adapters.remote.SOServices;
import qr.models.QualityRequirementPattern;
import retrofit2.Response;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the Adapter to connect to the PABRE-WS
 */
public class RequirementPatternAdapterImpl implements IRequirementPatternAdapter {
    private final SOServices mServices;

    public RequirementPatternAdapterImpl(SOServices soServices) {
        mServices = soServices;
    }


    @Override
    public List<QualityRequirementPattern> getAllRequirementPatterns() {
        List<QRPatternServer> l = null;
        try {
            Response<List<QRPatternServer>> s = mServices.getAllPatterns().execute();
            l = s.body();
        } catch (IOException e) {
            System.err.println("Exception on gettingPatternByID");
            e.printStackTrace();
        }
        return toGenericList(l);
    }


    @Override
    public QualityRequirementPattern getRequirementPatternByID(long id) {
        QRPatternServer s2 = null;
        try {
            Response<QRPatternServer> s = mServices.getAllPatterns(id).execute();
            s2 = s.body();
        } catch (IOException e) {
            System.err.println("Exception on gettingPatternByID");
            e.printStackTrace();
        }
        return s2 != null ? s2.toGenericModel() : null;
    }

    @Override
    public List<QualityRequirementPattern> getPatternsOfGivenClassifier(String name) {
        List<QRPatternServer> l = null;
        List<QualityRequirementPattern> l2 = new ArrayList<>();
        String SchemaName = "Schema Q-rapids";//TODO change to the select schema by Q-rapids
        String RootClassifierName = "Root"; //TODO change to the selected rootClassifier
        List<String> ls = new ArrayList<>();
        ls.add(SchemaName);
        ls.add(RootClassifierName);
        ls.add(name);
        try {
            Response<List<QRPatternServer>> s = mServices.getPatternsOfGivenClassifier(ls, true).execute();
            l = s.body();
        } catch (IOException e) {
            System.err.println("Exception on gettingPatternByID");
            e.printStackTrace();
        }

        return toGenericList(l);
    }

    @Override
    public boolean importCatalogue(String json) {
        boolean b = false;
        try {
            Gson g = new Gson();
            JsonObject js = g.fromJson(json, JsonObject.class);
            Response<Void> s = mServices.importCatalogue(js).execute();
            b = s.isSuccessful();
            //System.out.println("Response received: " + s.code());
        } catch (IOException e) {
            System.err.println("Exception in import");
            e.printStackTrace();
        }


        return b;
    }

    private List<QualityRequirementPattern> toGenericList(List<QRPatternServer> l) {
        List<QualityRequirementPattern> l2 = new ArrayList<>();
        if (l == null) return l2;
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i) != null)
                l2.add(l.get(i).toGenericModel());
        }
        return l2;
    }
}
