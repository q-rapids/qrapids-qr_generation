package qr.adapters.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import qr.models.Classifier;
import qr.models.Schema;

import java.util.ArrayList;
import java.util.List;

public class SchemaServer implements IServerModel{

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("rootClassifiers")
    @Expose
    private List<ClassifierServer> rootClassifiersServer;

    public SchemaServer() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ClassifierServer> getRootClassifiersServer() {
        return rootClassifiersServer;
    }

    public void setRootClassifiersServer(List<ClassifierServer> rootClassifiersServer) {
        this.rootClassifiersServer = rootClassifiersServer;
    }

    @Override
    public Schema toGenericModel() {
        Schema schema = new Schema();
        schema.setId(this.id);
        schema.setName(this.name);
        List<Classifier> rootClassifiers = new ArrayList<>();
        if (this.rootClassifiersServer != null) {
            for (ClassifierServer classifierServer : this.rootClassifiersServer) {
                rootClassifiers.add(classifierServer.toGenericModel());
            }
        }
        schema.setRootClassifiers(rootClassifiers);
        return schema;
    }
}
