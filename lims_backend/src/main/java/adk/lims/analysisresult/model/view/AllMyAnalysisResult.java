package adk.lims.analysisresult.model.view;

import java.time.LocalDate;

public class AllMyAnalysisResult {
    private Long id;
    private LocalDate dueDate;
    private String analysisName;

    public AllMyAnalysisResult() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getAnalysisName() {
        return analysisName;
    }

    public void setAnalysisName(String analysisName) {
        this.analysisName = analysisName;
    }
}
