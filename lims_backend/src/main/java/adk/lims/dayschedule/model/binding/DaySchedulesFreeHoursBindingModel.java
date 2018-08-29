package adk.lims.dayschedule.model.binding;

import javax.validation.constraints.Min;

public class DaySchedulesFreeHoursBindingModel {
    @Min(1)
    private Long analysisId;

    public DaySchedulesFreeHoursBindingModel() {
    }

    public Long getAnalysisId() {
        return analysisId;
    }

    public void setAnalysisId(Long analysisId) {
        this.analysisId = analysisId;
    }
}
