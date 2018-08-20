package adk.lims.analysis.model.entity;

import adk.lims.analysisresult.model.entity.AnalysisResult;
import adk.lims.consultation.model.entity.Consultation;
import adk.lims.user.employee.model.entity.Employee;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "analyzes")
public class Analysis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date createdOn;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    @Min(1)
    private int periodOfProduct;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="employee_id")
    private Employee employee;

    @OneToMany(mappedBy = "analysis")
    private List<AnalysisResult> results;

    @OneToMany(mappedBy = "analysis")
    private List<Consultation> consultations;

    @Column(nullable = false)
    private String type;

    public Analysis() {
        this.consultations = new ArrayList<>();
        this.results = new ArrayList<>();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getPeriodOfProduct() {
        return periodOfProduct;
    }

    public void setPeriodOfProduct(int periodOfProduct) {
        this.periodOfProduct = periodOfProduct;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<AnalysisResult> getResults() {
        return results;
    }

    public void setResults(List<AnalysisResult> results) {
        this.results = results;
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }
}
