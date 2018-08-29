package adk.lims.consultation.model.view;

import java.time.LocalDate;

public class PatientConsultationViewModel {
    private String analysisName;
    private int hour;
    private LocalDate dateOfConsultation;

    public PatientConsultationViewModel(String analysisName, int hour, LocalDate dateOfConsultation) {
        this.analysisName = analysisName;
        this.hour = hour;
        this.dateOfConsultation = dateOfConsultation;
    }

    public String getAnalysisName() {
        return analysisName;
    }

    public void setAnalysisName(String analysisName) {
        this.analysisName = analysisName;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public LocalDate getDateOfConsultation() {
        return dateOfConsultation;
    }

    public void setDateOfConsultation(LocalDate dateOfConsultation) {
        this.dateOfConsultation = dateOfConsultation;
    }
}
