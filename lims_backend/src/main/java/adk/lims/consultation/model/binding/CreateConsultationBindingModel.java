package adk.lims.consultation.model.binding;

import java.time.LocalDate;

public class CreateConsultationBindingModel {
    private LocalDate dateOfConsultation;
    private int hourOfConsultation;
    private Long analysisId;
    private Long patientId;

    public CreateConsultationBindingModel() {
    }

    public LocalDate getDateOfConsultation() {
        return dateOfConsultation;
    }

    public void setDateOfConsultation(LocalDate dateOfConsultation) {
        this.dateOfConsultation = dateOfConsultation;
    }

    public int getHourOfConsultation() {
        return hourOfConsultation;
    }

    public void setHourOfConsultation(int hourOfConsultation) {
        this.hourOfConsultation = hourOfConsultation;
    }

    public Long getAnalysisId() {
        return analysisId;
    }

    public void setAnalysisId(Long analysisId) {
        this.analysisId = analysisId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }
}
