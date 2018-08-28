package adk.lims.user.patient.model.entity;

import adk.lims.analysisresult.model.entity.AnalysisResult;
import adk.lims.consultation.model.entity.Consultation;
import adk.lims.user.abstractuser.model.User;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "patient")
    private List<AnalysisResult> analysisResults;

    @OneToMany(mappedBy = "patient")
    private List<Consultation> consultations;

    @OneToOne
    private User user;

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

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFullName(){
        return String.format("%s %s", this.getUser().getFirstName(), this.getUser().getLastName());
    }
}
