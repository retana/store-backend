package gt.retana.product.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.security.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Table(name = "orders")
@Entity
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String orderId = UUID.randomUUID().toString();
    private Integer customerId;
    private String productIds;
    private Integer quantity;
    private Double price;
    private String status;
    private Date createdAt = new Date();
}
