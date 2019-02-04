package qr.models;

public class Form {

    private String name;
    private String description;
    private String comments;
    private FixedPart fixedPart;

    public Form(String name, String description, String comments, FixedPart fixedPart) {
        this.name = name;
        this.description = description;
        this.comments = comments;
        this.fixedPart = fixedPart;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public FixedPart getFixedPart() {
        return fixedPart;
    }

    public void setFixedPart(FixedPart fixedPart) {
        this.fixedPart = fixedPart;
    }

    @Override
    public String toString() {
        return "Form{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", comments='" + comments + '\'' +
                ", fixedPart='" + fixedPart + '\'' +
                '}';
    }
}
