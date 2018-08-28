package adk.lims.analysis.model.view;

public class AnalysisDetailViewModel {
    private Long id;
    private String name;
    private String type;
    private Double price;
    private String description;
    private int periodOfProduct;

    public AnalysisDetailViewModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPeriodOfProduct() {
        return periodOfProduct;
    }

    public void setPeriodOfProduct(int periodOfProduct) {
        this.periodOfProduct = periodOfProduct;
    }
}
