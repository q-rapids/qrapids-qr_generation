package qr.adapters.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


/**
 * Created by Awais Iqbal on 26/10/2017.
 * Class used to create something new or update, will be used sometime in the future
 */
public class Forms {

    public static class TMPForm {
        //TODO Complete the creation of the pattern
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
        private Boolean editable;

        @SerializedName("versions")
        @Expose
        private List<VersionCreation> versions;

    }

    public static class VersionCreation {

        @SerializedName("versions")
        @Expose
        private String versionDate;

        @SerializedName("author")
        @Expose
        private String author;

        @SerializedName("goal")
        @Expose
        private String goal;

        @SerializedName("numInstaces")
        @Expose
        private Integer numInstaces;

        @SerializedName("available")
        @Expose
        private boolean available;

        @SerializedName("statsNumInstances")
        @Expose
        private int statsNumInstances;

        @SerializedName("statsNumAssociates")
        @Expose
        private int statsNumAssociates;

        @SerializedName("forms")
        @Expose
        private List<FormCreation> forms;


    }

    public static class FormCreation {

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
        private Integer numInstances;

        @SerializedName("statsNumInstances")
        @Expose
        private Integer statsNumInstances;

        @SerializedName("statsNumAssociates")
        @Expose
        private Integer statsNumAssociates;

        @SerializedName("rank")
        @Expose
        private Integer rank;

        @SerializedName("fixedPart")
        @Expose
        private FixedPartCreation fixedPart;

    }

    public static class FixedPartCreation {

        @SerializedName("patternText")
        @Expose
        private String patternText;

        @SerializedName("questionText")
        @Expose
        private String questionText;

        @SerializedName("numInstances")
        @Expose
        private Integer numInstances;

        @SerializedName("available")
        @Expose
        private Boolean available;

        @SerializedName("statsNumInstances")
        @Expose
        private Integer statsNumInstances;

        @SerializedName("parameters")
        @Expose
        private List<ParameterCreation> parameters;

    }

    public static class ParameterCreation {

        @SerializedName("name")
        @Expose
        private String name;

        @SerializedName("description")
        @Expose
        private String description;

        @SerializedName("metricId")
        @Expose
        private String metricId;
    }


}

