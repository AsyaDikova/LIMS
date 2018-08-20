package adk.lims.consultation.model.entity;

import adk.lims.analysis.model.entity.Analysis;
import adk.lims.user.patient.model.entity.Patient;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "consultations")
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date createdOn;

    private Date dateOfConsultation;

    private int hourOfConsultation;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="analysis_id")
    private Analysis analysis;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="patient_id")
    private Patient patient;

    public Consultation() {
        this.createdOn = new Date();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getDateOfConsultation() {
        return dateOfConsultation;
    }

    public void setDateOfConsultation(Date dateOfConsultation) {
        this.dateOfConsultation = dateOfConsultation;
    }

    public int getHourOfConsultation() {
        return hourOfConsultation;
    }

    public void setHourOfConsultation(int hourOfConsultation) {
        this.hourOfConsultation = hourOfConsultation;
    }

    public Analysis getAnalysis() {
        return analysis;
    }

    public void setAnalysis(Analysis analysis) {
        this.analysis = analysis;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
