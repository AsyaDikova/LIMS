package adk.lims.analysisresult.model.entity;

import adk.lims.analysis.model.entity.Analysis;
import adk.lims.user.patient.model.entity.Patient;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "analysis_results")
public class AnalysisResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="patient_id")
    private Patient patient;

    private Date createdOn;

    private Date dueDate;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String note;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="analysis_id")
    private Analysis analysis;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    public AnalysisResult() {
        this.createdOn = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Analysis getAnalysis() {
        return analysis;
    }

    public void setAnalysis(Analysis analysis) {
        this.analysis = analysis;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
