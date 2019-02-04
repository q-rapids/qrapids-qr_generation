package qr.models;

public class FixedPart {

    private String formText;

    public FixedPart(String formText) {
        this.formText = formText;
    }

    public String getFormText() {
        return formText;
    }

    public void setFormText(String formText) {
        this.formText = formText;
    }

    @Override
    public String toString() {
        return "FixedPart{" +
                "formText='" + formText + '\'' +
                '}';
    }
}
