package gt.retana.product.model.repository;

import gt.retana.product.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order,Integer> {
    @Override
    List<Order> findAll();
    List<Order> findByCustomerId(Integer customerId);
}
