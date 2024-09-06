package gt.retana.product.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {
    private Integer customerId;
    private String productIds;
    private Integer quantity;
    private Double price;
    private String status;
}