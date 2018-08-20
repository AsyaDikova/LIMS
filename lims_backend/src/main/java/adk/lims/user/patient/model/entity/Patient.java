package adk.lims.user.patient.model.entity;

import adk.lims.analysisresult.model.entity.AnalysisResult;
import adk.lims.consultation.model.entity.Consultation;
import adk.lims.user.abstractuser.model.User;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patients")
public class Patient extends User{

    @OneToMany(mappedBy = "patient")
    private List<AnalysisResult> analysisResults;

    @OneToMany(mappedBy = "patient")
    private List<Consultation> consultations;

    public Patient() {
        super();
        this.analysisResults = new ArrayList<>();
        this.consultations = new ArrayList<>();
    }

    public List<AnalysisResult> getAnalysisResults() {
        return analysisResults;
    }

    public void setAnalysisResults(List<AnalysisResult> analysisResults) {
        this.analysisResults = analysisResults;
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }
}
