package qr.models;

import java.util.List;

public class FixedPart {

    private String formText;
    private List<Param> parameters;

    public FixedPart(String formText, List<Param> parameters) {
        this.formText = formText;
        this.parameters = parameters;
    }

    public String getFormText() {
        return formText;
    }

    public void setFormText(String formText) {
        this.formText = formText;
    }

    public List<Param> getParameters() {
        return parameters;
    }

    public void setParameters(List<Param> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "FixedPart{" +
                "formText='" + formText + '\'' +
                '}';
    }
}
