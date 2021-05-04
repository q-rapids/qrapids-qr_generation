package qr.adapters;

import com.google.gson.Gson;
import qr.adapters.models.ClassifierServer;
import qr.adapters.models.ClassifierServerEdit;
import qr.adapters.models.QRPatternServer;
import qr.adapters.models.QRPatternServerEdit;
import qr.adapters.models.SchemaServer;
import qr.adapters.remote.SOServices;
import qr.models.Classifier;
import qr.models.QualityRequirementPattern;
import qr.models.Schema;
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
            Response<List<QRPatternServer>> s = mServices.getAllPatterns(true).execute();
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
    public int createRequirementPattern(QualityRequirementPattern newPattern) {
        QRPatternServerEdit.PatternEdit p = new QRPatternServerEdit.PatternEdit(newPattern);
        Integer newId = null;
        try {
            Response<JsonObject> s = mServices.createPattern(p).execute();
            newId = s.body().get("id").getAsInt();
        } catch (IOException e) {
            System.err.println("Exception on creatingPattern");
            e.printStackTrace();
        }
        return newId;
    }

    @Override
    public void updateRequirementPattern(long id, QualityRequirementPattern editedPattern) {
        QRPatternServer originalPattern = null;
        try {
            Response<QRPatternServer> s = mServices.getAllPatterns(id).execute();
            originalPattern = s.body();

            if(originalPattern != null) {
                QRPatternServerEdit.PatternEdit p = new QRPatternServerEdit.PatternEdit(originalPattern);
                p.updateValues(editedPattern);
                mServices.updatePattern(id, p).execute();
            }
        } catch (IOException e) {
            System.err.println("Exception on updatingPattern");
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRequirementPattern(long id) {
        try {
            mServices.deletePattern(id).execute();
        } catch (IOException e) {
            System.err.println("Exception on deletingPattern");
            e.printStackTrace();
        }
    }

    @Override
    public void updateClassifier(long schemaId, long id, String name, Integer pos, List<Integer> patternsList) {
        ClassifierServerEdit classifier = new ClassifierServerEdit(name, pos);
        classifier.setRequirementPatternsId(patternsList);
        try {
            mServices.updateClassifier(schemaId, id, classifier).execute();
        } catch (IOException e) {
            System.err.println("Exception on updatingClassifier");
            e.printStackTrace();
        }
    }

    @Override
    public List<QualityRequirementPattern> getPatternsOfGivenClassifier(String name) {
        List<QRPatternServer> l = null;
        List<QualityRequirementPattern> l2 = new ArrayList<>();
        //String SchemaName = "Schema Q-rapids";//TODO change to the select schema by Q-rapids
        //String RootClassifierName = "Root"; //TODO change to the selected rootClassifier
        List<String> ls = new ArrayList<>();
        //ls.add(SchemaName);
        //ls.add(RootClassifierName);
        ls.add(name);
        try {
            Response<List<QRPatternServer>> s = mServices.getPatternsOfGivenClassifier(ls, true, true).execute();
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

    @Override
    public Schema getSchemaByName(String name) {
        Schema schema = null;
        try {
            List<String> names = new ArrayList<>();
            names.add(name);
            Response<List<SchemaServer>> response = mServices.getSchemaByName(names).execute();
            List<SchemaServer> schemasServer = response.body();
            schema = schemasServer.get(0).toGenericModel();
        } catch (IOException e) {
            System.err.println("Exception on getSchemaByName");
            e.printStackTrace();
        }
        return schema;
    }

    @Override
    public Classifier getClassifierById(long schemaId, long id) {
        ClassifierServer classifier = null;
        try {
            Response<ClassifierServer> response = mServices.getClassifier(schemaId, id).execute();
            classifier = response.body();
        } catch (IOException e) {
            System.err.println("Exception on gettingClassifierByID");
            e.printStackTrace();
        }
        return classifier != null ? classifier.toGenericModel() : null;
    }

    @Override
    public void createClassifier(long schemaId, String name, long parentId) {
        try {
            Response<ClassifierServer> response = mServices.getClassifier(schemaId, parentId).execute();
            ClassifierServer parent = response.body();

            List<ClassifierServerEdit> editedParentClassifiers = new ArrayList<>();
            for (ClassifierServer c1 : parent.getInternalClassifiersServer()) {
                editedParentClassifiers.add(toClassifierServerEdit(c1));
            }

            editedParentClassifiers.add(new ClassifierServerEdit(name, editedParentClassifiers.size()));

            ClassifierServerEdit editedParent = new ClassifierServerEdit(parent.getName(), parent.getPos());
            editedParent.setInternalClassifiers(editedParentClassifiers);

            mServices.updateClassifier(schemaId, parentId, editedParent).execute();
        } catch (IOException e) {
            System.err.println("Exception on updatingClassifier");
            e.printStackTrace();
        }
    }

    public void deleteClassifier(long schemaId, long id, long parentId) {
        try {
            Response<ClassifierServer> response = mServices.getClassifier(schemaId, parentId).execute();
            ClassifierServer parent = response.body();

            List<ClassifierServerEdit> editedParentClassifiers = new ArrayList<>();
            for (ClassifierServer c1 : parent.getInternalClassifiersServer()) {
                if (c1.getId() != id) {
                    editedParentClassifiers.add(toClassifierServerEdit(c1));
                }
            }

            ClassifierServerEdit editedParent = new ClassifierServerEdit(parent.getName(), parent.getPos());
            editedParent.setInternalClassifiers(editedParentClassifiers);

            mServices.updateClassifier(schemaId, parentId, editedParent).execute();
        } catch (IOException e) {
            System.err.println("Exception on deletingClassifier");
            e.printStackTrace();
        }
    }

    private ClassifierServerEdit toClassifierServerEdit(ClassifierServer classifier) {
        ClassifierServerEdit classifierEdit1 = new ClassifierServerEdit(classifier.getName(), classifier.getPos());
        if (classifier.getRequirementPatternsServer().isEmpty()) { //if contains classifiers
            List<ClassifierServerEdit> classifierEdit1List = new ArrayList<>();
            for (ClassifierServer c2 : classifier.getInternalClassifiersServer()) {
                classifierEdit1List.add(toClassifierServerEdit(c2));
            }
            classifierEdit1.setInternalClassifiers(classifierEdit1List);
        } else { //if contains patterns
            List<Integer> idPatterns = new ArrayList<>();
            for (QRPatternServer pattern : classifier.getRequirementPatternsServer()) {
                idPatterns.add(pattern.getId());
            }
            classifierEdit1.setRequirementPatternsId(idPatterns);
        }
        return classifierEdit1;
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
