package adk.lims.analysis.model.binding;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AddAnalysisBindingModel {
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotNull
    private Double price;
    @NotNull
    private int periodOfProduct;
    @NotEmpty
    private String type;

    public AddAnalysisBindingModel() {
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
}
