package qr.adapters.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import qr.models.FixedPart;
import qr.models.Form;
import qr.models.Param;
import qr.models.QualityRequirementPattern;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created by Awais Iqbal on 26/10/2017.
 * Class used to create something new or update, will be used sometime in the future
 */
public class Forms {

    public static class QRPatternCreation {

        @SerializedName("id")
        @Expose
        private int id;

        @SerializedName("name")
        @Expose
        private String name;

        @SerializedName("description")
        @Expose
        private String description;

        @SerializedName("comments")
        @Expose
        private String comments;

        @SerializedName("editable")
        @Expose
        private boolean editable;

        @SerializedName("versions")
        @Expose
        private List<VersionCreation> versions;

        public QRPatternCreation(QualityRequirementPattern pattern) {
            //no id
            this.name = pattern.getName();
            this.description = pattern.getDescription();
            this.comments = pattern.getComments();
            this.editable = false;
            //version start
            List<VersionCreation> versions = new ArrayList<>();
            VersionCreation v = new VersionCreation(pattern);
            versions.add(v);
            //version end
            this.versions = versions;
        }

        public QRPatternCreation(QRPatternServer patternServer) {
            this.id = patternServer.getId();
            this.name = patternServer.getName();
            this.description = patternServer.getDescription();
            this.comments = patternServer.getComments();
            this.editable = false;
            //version start
            List<VersionCreation> versions = new ArrayList<>();
            VersionCreation v = new VersionCreation(patternServer);
            versions.add(v);
            //version end
            this.versions = versions;
        }

        public void updateValues(QualityRequirementPattern qrPattern) {
            this.id = qrPattern.getId();
            this.name = qrPattern.getName();
            this.comments = qrPattern.getComments();
            this.description = qrPattern.getDescription();

            this.versions.get(0).updateValues(qrPattern);
        }

    }

    public static class VersionCreation {

        @SerializedName("id")
        @Expose
        private int id;

        @SerializedName("versionDate")
        @Expose
        private String versionDate;

        @SerializedName("author")
        @Expose
        private String author;

        @SerializedName("goal")
        @Expose
        private String goal;

        @SerializedName("numInstances")
        @Expose
        private int numInstances;

        @SerializedName("available")
        @Expose
        private boolean available;

        @SerializedName("statsNumInstances")
        @Expose
        private int statsNumInstances;

        @SerializedName("statsNumAssociates")
        @Expose
        private int statsNumAssociates;

        @SerializedName("artifactsRelation")
        @Expose
        private String artifactsRelation;

        @SerializedName("forms")
        @Expose
        private List<FormCreation> forms;

        @SerializedName("costFunctions")
        @Expose
        private List<Map<String, String>> costFunctions;

        public VersionCreation(QualityRequirementPattern pattern) {
            //no id
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            this.versionDate = dateFormat.format(date);
            this.author = "Q-Rapids Dashboard";
            this.goal = pattern.getGoal();
            this.numInstances = 0;
            this.available = true;
            this.statsNumInstances = 0;
            this.statsNumAssociates = 0;
            this.artifactsRelation = "";
            //add creation of costFunctions if necessary
            //form start
            List<FormCreation> forms = new ArrayList<>();
            for(int i=0; i<pattern.getForms().size(); i++) {
                FormCreation f = new FormCreation(pattern.getForms().get(i), this.versionDate);
                forms.add(f);
            }
            //form end
            this.forms = forms;
        }

        public VersionCreation(QRPatternServer patternServer) {
            this.id = patternServer.getVersions().get(0).getId();
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            this.versionDate = dateFormat.format(date);
            this.author = patternServer.getAuthor();
            this.goal = patternServer.getGoal();
            this.numInstances = 0;
            this.available = true;
            this.statsNumInstances = 0;
            this.statsNumAssociates = 0;
            this.artifactsRelation = "";
            this.costFunctions = patternServer.getCostFunction();
            //form start
            List<FormCreation> forms = new ArrayList<>();
            for(int i=0; i<patternServer.getForms().size(); i++) {
                FormCreation f = new FormCreation(patternServer.getForms().get(i), this.versionDate);
                forms.add(f);
            }
            //form end
            this.forms = forms;
        }

        public void updateValues(QualityRequirementPattern qrPattern) {
            this.goal = qrPattern.getGoal();
            //add update of costFunctions if necessary

            for (int i=0; i<qrPattern.getForms().size(); i++){
                this.forms.get(i).updateValues(qrPattern.getForms().get(i));
            }
        }

    }

    public static class FormCreation {

        @SerializedName("id")
        @Expose
        private int id;

        @SerializedName("name")
        @Expose
        private String name;

        @SerializedName("description")
        @Expose
        private String description;

        @SerializedName("comments")
        @Expose
        private String comments;

        @SerializedName("author")
        @Expose
        private String author;

        @SerializedName("modificationDate")
        @Expose
        private String modificationDate;

        @SerializedName("numInstances")
        @Expose
        private int numInstances;

        @SerializedName("statsNumInstances")
        @Expose
        private int statsNumInstances;

        @SerializedName("statsNumAssociates")
        @Expose
        private int statsNumAssociates;

        @SerializedName("pos")
        @Expose
        private int pos;

        @SerializedName("available")
        @Expose
        private boolean available;

        @SerializedName("fixedPart")
        @Expose
        private FixedPartCreation fixedPart;

        public FormCreation(Form form, String date) {
            // no id
            this.name = form.getName();
            this.description = form.getDescription();
            this.comments = form.getComments();
            this.author = "Q-Rapids Dashboard";
            this.modificationDate = date;
            this.numInstances = 0;
            this.statsNumInstances = 0;
            this.statsNumAssociates = 0;
            this.pos = 0;
            this.available = true;
            this.fixedPart = new FixedPartCreation(form.getFixedPart());
        }

        public FormCreation(FormServer formServer, String date) {
            this.id = formServer.getId();
            this.name = formServer.getName();
            this.description = formServer.getDescription();
            this.comments = formServer.getComments();
            this.author = "Q-Rapids Dashboard";
            this.modificationDate = date;
            this.numInstances = 0;
            this.statsNumInstances = 0;
            this.statsNumAssociates = 0;
            this.pos = 0;
            this.available = true;
            this.fixedPart = new FixedPartCreation(formServer.getFixedPart());
        }

        public void updateValues(Form qrpForm) {
            this.name = qrpForm.getName();
            this.description = qrpForm.getDescription();
            this.comments = qrpForm.getComments();

            this.fixedPart.updateValues(qrpForm.getFixedPart());
        }

    }

    public static class FixedPartCreation {

        @SerializedName("id")
        @Expose
        private int id;

        @SerializedName("patternText")
        @Expose
        private String patternText;

        @SerializedName("questionText")
        @Expose
        private String questionText;

        @SerializedName("numInstances")
        @Expose
        private int numInstances;

        @SerializedName("available")
        @Expose
        private boolean available;

        @SerializedName("statsNumInstances")
        @Expose
        private int statsNumInstances;

        @SerializedName("artifactsRelation")
        @Expose
        private String artifactsRelation;

        @SerializedName("parameters")
        @Expose
        private List<ParameterCreation> parameters;

        public FixedPartCreation(FixedPart fixedPart) {
            //no id
            this.patternText = fixedPart.getFormText();
            this.questionText = "";
            this.numInstances = 0;
            this.available = true;
            this.statsNumInstances = 0;
            this.artifactsRelation = "";
            List<ParameterCreation> parameters = new ArrayList<>();
            //parameters start
            for(int i=0; i<fixedPart.getParameters().size(); i++) {
                ParameterCreation p = new ParameterCreation(fixedPart.getParameters().get(i));
                parameters.add(p);
            }
            //parameters end
            this.parameters = parameters;
        }

        public FixedPartCreation(FixedPartServer fixedPart) {
            this.id = Integer.parseInt(fixedPart.getId());
            this.patternText = fixedPart.getFormText();
            this.questionText = "";
            this.numInstances = 0;
            this.available = true;
            this.statsNumInstances = 0;
            this.artifactsRelation = "";
            List<ParameterCreation> parameters = new ArrayList<>();
            //parameters start
            for(int i=0; i<fixedPart.getParameters().size(); i++) {
                ParameterCreation p = new ParameterCreation(fixedPart.getParameters().get(i));
                parameters.add(p);
            }
            //parameters end
            this.parameters = parameters;
        }

        public void updateValues(FixedPart qrpFixedPart) {
            this.patternText = qrpFixedPart.getFormText();

            for (int i=0; i<qrpFixedPart.getParameters().size(); i++){
                this.parameters.get(i).updateValues(qrpFixedPart.getParameters().get(i));
            }
        }

    }

    public static class ParameterCreation {

        @SerializedName("id")
        @Expose
        private int id;

        @SerializedName("name")
        @Expose
        private String name;

        @SerializedName("correctnessCondition")
        @Expose
        private String correctnessCondition;

        @SerializedName("description")
        @Expose
        private String description;

        @SerializedName("metricId")
        @Expose
        private int metricId;

        public ParameterCreation(Param parameter) {
            //no id
            this.name = parameter.getName();
            this.correctnessCondition = parameter.getCorrectnessCondition();
            this.description = parameter.getDescription();
            this.metricId = parameter.getMetricId();
        }

        public ParameterCreation(ParameterServer parameterServer) {
            this.id = parameterServer.getId();
            this.name = parameterServer.getName();
            this.correctnessCondition = parameterServer.getCorrectnessCondition();
            this.description = parameterServer.getDescription();
            this.metricId = parameterServer.getMetric().getId();
        }

        public void updateValues(Param qrpParameter) {
            this.name = qrpParameter.getName();
            this.correctnessCondition = qrpParameter.getCorrectnessCondition();
            this.description = qrpParameter.getDescription();
            this.metricId = qrpParameter.getMetricId();
        }

    }

}

