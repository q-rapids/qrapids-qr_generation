package qr.adapters;

import com.google.gson.Gson;
import qr.adapters.models.ClassifierServer;
import qr.adapters.models.ClassifierServerEdit;
import qr.adapters.models.MetricServer;
import qr.adapters.models.QRPatternServer;
import qr.adapters.models.QRPatternServerEdit;
import qr.adapters.models.SchemaServer;
import qr.adapters.remote.SOServices;
import qr.models.Classifier;
import qr.models.Metric;
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
                editedParentClassifiers.add(toClassifierServerEdit(c1, 0));
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

    @Override
    public void updateClassifierWithPatterns(long schemaId, long id, String name, Integer pos, List<Integer> patternsList) {
        ClassifierServerEdit classifier = new ClassifierServerEdit(name, pos);
        classifier.setRequirementPatternsId(patternsList);
        try {
            mServices.updateClassifier(schemaId, id, classifier).execute();
        } catch (IOException e) {
            System.err.println("Exception on updatingClassifierWithPatterns");
            e.printStackTrace();
        }
    }

    @Override
    public void updateClassifier(long schemaId, long id, String name) {
        try {
            Response<ClassifierServer> response = mServices.getClassifier(schemaId, id).execute();
            ClassifierServer classifier = response.body();
            ClassifierServerEdit editedClassifier = toClassifierServerEdit(classifier, 0);
            editedClassifier.setName(name);
            mServices.updateClassifier(schemaId, id, editedClassifier).execute();
        } catch (IOException e) {
            System.err.println("Exception on updatingClassifier");
            e.printStackTrace();
        }
    }

    @Override
    public void updateAndMoveClassifier(long schemaId, long id, String name, long oldParentId, long newParentId) {
        try {
            Response<ClassifierServer> response = mServices.getClassifier(schemaId, oldParentId).execute();
            ClassifierServer oldParent = response.body();

            //if old parent is root and new parent not, we should follow a different way
            boolean oldParentIsRoot = (oldParent.getName().equals("Root"));

            List<ClassifierServerEdit> editedOldParentClassifiers = new ArrayList<>();
            int posOffset = 0;
            ClassifierServerEdit movedClassifier = null;
            for (ClassifierServer c1 : oldParent.getInternalClassifiersServer()) {
                if (c1.getId() != id) {
                    editedOldParentClassifiers.add(toClassifierServerEdit(c1, posOffset));
                } else {
                    posOffset -= 1;
                    movedClassifier = toClassifierServerEdit(c1, 0);
                }
            }

            ClassifierServerEdit editedOldParent = new ClassifierServerEdit(oldParent.getName(), oldParent.getPos());
            editedOldParent.setInternalClassifiers(editedOldParentClassifiers);

            if (!oldParentIsRoot) {
                mServices.updateClassifier(schemaId, oldParentId, editedOldParent).execute();
            }

            Response<ClassifierServer> response2 = mServices.getClassifier(schemaId, newParentId).execute();
            ClassifierServer newParent = response2.body();

            List<ClassifierServerEdit> editedNewParentClassifiers = new ArrayList<>();
            for (ClassifierServer c1 : newParent.getInternalClassifiersServer()) {
                editedNewParentClassifiers.add(toClassifierServerEdit(c1, 0));
            }
            if (movedClassifier != null) {
                movedClassifier.setName(name);
                movedClassifier.setPos(editedNewParentClassifiers.size());
                editedNewParentClassifiers.add(movedClassifier);
            }

            ClassifierServerEdit editedNewParent = new ClassifierServerEdit(newParent.getName(), newParent.getPos());
            editedNewParent.setInternalClassifiers(editedNewParentClassifiers);

            if (!oldParentIsRoot) {
                mServices.updateClassifier(schemaId, newParentId, editedNewParent).execute();
            } else {
                //integrate the new parent into the old parent and save changes
                for (int i=0; i<editedOldParentClassifiers.size(); i++) {
                    if (editedOldParentClassifiers.get(i).getName().equals(editedNewParent.getName())) {
                        editedNewParent.setPos(i);
                        editedOldParentClassifiers.set(i, editedNewParent);
                    }
                }
                editedOldParent.setInternalClassifiers(editedOldParentClassifiers);
                mServices.updateClassifier(schemaId, oldParentId, editedOldParent).execute();
            }
        } catch (IOException e) {
            System.err.println("Exception on updatingAndMovingClassifier");
            e.printStackTrace();
        }
    }

    @Override
    public void deleteClassifier(long schemaId, long id, long parentId) {
        try {
            Response<ClassifierServer> response = mServices.getClassifier(schemaId, parentId).execute();
            ClassifierServer parent = response.body();

            List<ClassifierServerEdit> editedParentClassifiers = new ArrayList<>();
            int posOffset = 0;
            for (ClassifierServer c1 : parent.getInternalClassifiersServer()) {
                if (c1.getId() != id) {
                    editedParentClassifiers.add(toClassifierServerEdit(c1, posOffset));
                } else {
                    posOffset -= 1;
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

    @Override
    public List<Metric> getAllMetrics() {
        List<MetricServer> metricServerList = null;
        try {
            Response<List<MetricServer>> s = mServices.getAllMetrics().execute();
            metricServerList = s.body();
        } catch (IOException e) {
            System.err.println("Exception on gettingAllMetrics");
            e.printStackTrace();
        }

        List<Metric> metricList = new ArrayList<>();
        if (metricServerList != null) {
            for (MetricServer ms : metricServerList) {
                metricList.add(ms.toGenericModel());
            }
        }
        return metricList;
    }

    @Override
    public Metric getMetricById(long id) {
        MetricServer metric = null;
        try {
            Response<MetricServer> s = mServices.getMetric(id).execute();
            metric = s.body();
        } catch (IOException e) {
            System.err.println("Exception on gettingMetricByID");
            e.printStackTrace();
        }
        return metric != null ? metric.toGenericModel() : null;
    }

    @Override
    public boolean createMetric(Metric newMetric) {
        MetricServer metricServer = new MetricServer();
        metricServer.setName(newMetric.getName());
        metricServer.setDescription(newMetric.getDescription());
        metricServer.setComments("");
        String type = newMetric.getType();
        if (type.equals("integer") || type.equals("float")) {
            metricServer.setMinValue(newMetric.getMinValue());
            metricServer.setMaxValue(newMetric.getMaxValue());
        } else if (type.equals("domain")) {
            metricServer.setPossibleValues(newMetric.getPossibleValues());
        }
        try {
            Response<Void> res = mServices.createMetric(type, metricServer).execute();
            if (!res.isSuccessful()) {
                return false;
            }
        } catch (IOException e) {
            System.err.println("Exception on creatingMetric");
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean updateMetric(long id, Metric metric) {
        MetricServer metricServer = new MetricServer();
        metricServer.setName(metric.getName());
        metricServer.setDescription(metric.getDescription());
        metricServer.setComments("");
        String type = metric.getType();
        if (type.equals("integer") || type.equals("float")) {
            metricServer.setMinValue(metric.getMinValue());
            metricServer.setMaxValue(metric.getMaxValue());
        } else if (type.equals("domain")) {
            metricServer.setPossibleValues(metric.getPossibleValues());
        }
        try {
            Response<Void> res = mServices.updateMetric(id, metricServer).execute();
            if (!res.isSuccessful()) {
                return false;
            }
        } catch (IOException e) {
            System.err.println("Exception on updatingMetric");
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean deleteMetric(long id) {
        try {
            Response<Void> res = mServices.deleteMetric(id).execute();
            if (!res.isSuccessful()) {
                return false;
            }
        } catch (IOException e) {
            System.err.println("Exception on deletingMetric");
            e.printStackTrace();
        }
        return true;
    }

    private ClassifierServerEdit toClassifierServerEdit(ClassifierServer classifier, int posOffset) {
        ClassifierServerEdit classifierEdit1 = new ClassifierServerEdit(classifier.getName(), classifier.getPos()+posOffset);
        if (classifier.getRequirementPatternsServer().isEmpty()) { //if contains classifiers
            List<ClassifierServerEdit> classifierEdit1List = new ArrayList<>();
            for (ClassifierServer c2 : classifier.getInternalClassifiersServer()) {
                classifierEdit1List.add(toClassifierServerEdit(c2, 0));
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
