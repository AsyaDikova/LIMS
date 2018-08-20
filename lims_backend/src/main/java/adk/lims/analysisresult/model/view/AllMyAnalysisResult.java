package adk.lims.analysisresult.model.view;

import java.util.Date;

public class AllMyAnalysisResult {
    private Long id;
    private Date dueDate;
    private String analysisName;

    public AllMyAnalysisResult() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getAnalysisName() {
        return analysisName;
    }

    public void setAnalysisName(String analysisName) {
        this.analysisName = analysisName;
    }
}
