package ibf2022.assessment.paf.batch3.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class Order {
    private Integer beerId;

    @NotNull(message = "Cannot be null")
    @Min(value = 0, message = "Cannot be less than 0")
    private Integer quantity;

    public Integer getBeerId() {
        return beerId;
    }
    public void setBeerId(Integer beerId) {
        this.beerId = beerId;
    }

    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Order [beerId=" + beerId + ", quantity=" + quantity + "]";
    }
    

    
}
