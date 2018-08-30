package adk.lims.analysisresult.model.binding;

import javax.validation.constraints.NotNull;

public class CreateAnalysisResult {
    private Long patientId;
    private Long analysisId;

    public CreateAnalysisResult() {
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getAnalysisId() {
        return analysisId;
    }

    public void setAnalysisId(Long analysisId) {
        this.analysisId = analysisId;
    }
}
