package adk.lims.analysis.model.binding;

public class AddAnalysisBindingModel {
    private String name;
    private String description;
    private Double price;
    private int periodOfProduct;
    private String type;
    private Long employeeId;

    public AddAnalysisBindingModel() {
    }

    public AddAnalysisBindingModel(String name, String description, Double price, int periodOfProduct, String type) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.periodOfProduct = periodOfProduct;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
}
