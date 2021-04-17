package qr.adapters.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import qr.models.Form;
import qr.models.QualityRequirementPattern;

import java.text.SimpleDateFormat;
import java.util.*;

public class QRPatternServerEdit {

    public static class PatternEdit {

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
        private List<VersionEdit> versions;

        public PatternEdit(QRPatternServer patternServer) {
            this.id = patternServer.getId();
            this.name = patternServer.getName();
            this.description = patternServer.getDescription();
            this.comments = patternServer.getComments();
            this.editable = false;
            //version start
            List<VersionEdit> versions = new ArrayList<>();
            VersionEdit v = new VersionEdit(patternServer);
            versions.add(v);
            //version end
            this.versions = versions;
        }

        public void updateValues(QualityRequirementPattern qrPattern) {
            this.id = qrPattern.getId();
            this.name = qrPattern.getName();
            this.comments = qrPattern.getComments();
            this.description = qrPattern.getDescription();

            VersionEdit version = this.versions.get(0);
            version.setGoal(qrPattern.getGoal());
            //add update of costFunctions if necessary

            for (int i=0; i< qrPattern.getForms().size(); i++){
                Form qrpForm = qrPattern.getForms().get(i);
                FormEdit form = version.getForms().get(i);
                form.setName(qrpForm.getName());
                form.setDescription(qrpForm.getDescription());
                form.setComments(qrpForm.getComments());

                form.getFixedPart().setPatternText(qrpForm.getFixedPart().getFormText());
            }
        }

    }

    public static class VersionEdit {

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
        private List<FormEdit> forms;

        @SerializedName("costFunctions")
        @Expose
        private List<Map<String, String>> costFunctions;

        public VersionEdit(QRPatternServer patternServer) {
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
            List<FormEdit> forms = new ArrayList<>();
            for(int i=0; i<patternServer.getForms().size(); i++) {
                FormEdit f = new FormEdit(patternServer.getForms().get(i), this.versionDate);
                forms.add(f);
            }
            //form end
            this.forms = forms;
        }

        public void setGoal(String goal) {
            this.goal = goal;
        }

        public List<FormEdit> getForms() {
            return forms;
        }

    }

    public static class FormEdit {

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
        private FixedPartEdit fixedPart;

        public FormEdit(FormServer formServer, String date) {
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
            this.fixedPart = new FixedPartEdit(formServer.getFixedPart());
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public FixedPartEdit getFixedPart() {
            return fixedPart;
        }

    }

    public static class FixedPartEdit {

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
        private List<ParameterEdit> parameters;

        public FixedPartEdit(FixedPartServer fixedPart) {
            this.id = Integer.parseInt(fixedPart.getId());
            this.patternText = fixedPart.getFormText();
            this.questionText = "";
            this.numInstances = 0;
            this.available = true;
            this.statsNumInstances = 0;
            this.artifactsRelation = "";
            List<ParameterEdit> parameters = new ArrayList<>();
            //parameters start
            for(int i=0; i<fixedPart.getParameters().size(); i++) {
                ParameterEdit p = new ParameterEdit(fixedPart.getParameters().get(i));
                parameters.add(p);
            }
            //parameters end
            this.parameters = parameters;
        }

        public void setPatternText(String patternText) {
            this.patternText = patternText;
        }

    }

    public static class ParameterEdit {

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

        public ParameterEdit(ParameterServer parameterServer) {
            this.id = parameterServer.getId();
            this.name = parameterServer.getName();
            this.correctnessCondition = parameterServer.getCorrectnessCondition();
            this.description = parameterServer.getDescription();
            this.metricId = parameterServer.getMetric().getId();
        }

    }

}
