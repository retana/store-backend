package gt.retana.product.controller;

import gt.retana.product.dto.OrderDTO;
import gt.retana.product.model.Order;
import gt.retana.product.model.Product;
import gt.retana.product.service.OrderService;
import gt.retana.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


@RequestMapping("/api/v1/order")
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;


    @GetMapping("/{id}")
    public List<Order> getOrdersByCustomer(@PathVariable Integer id) {
        return orderService.findByCustomerId(id);
    }
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
        Order savedOrder = orderService.save(order);
        return ResponseEntity.ok(savedOrder);
    }

}
