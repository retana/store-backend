package gt.retana.product.service;

import gt.retana.product.model.Order;
import gt.retana.product.model.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private  OrderRepository orderRepository;
    public Order save(Order order) {
        return orderRepository.save(order);
    }
    public List<Order> findAll() {
        return orderRepository.findAll();
    }
    public List<Order> findByCustomerId(Integer customerId){
        return orderRepository.findByCustomerId(customerId);
    }
}
