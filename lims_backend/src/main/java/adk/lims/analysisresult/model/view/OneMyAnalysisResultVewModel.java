package adk.lims.analysisresult.model.view;

import java.util.Date;

public class OneMyAnalysisResultVewModel {
    private String description;
    private String analysisName;
    private String note;
    private Date dueDate;

    public OneMyAnalysisResultVewModel() {
    }

    public OneMyAnalysisResultVewModel(String description, String analysisName, String note, Date dueDate) {
        this.description = description;
        this.analysisName = analysisName;
        this.note = note;
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnalysisName() {
        return analysisName;
    }

    public void setAnalysisName(String analysisName) {
        this.analysisName = analysisName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
