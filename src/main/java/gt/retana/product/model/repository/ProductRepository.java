package gt.retana.product.model.repository;

import gt.retana.product.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    @Override
    List<Product> findAll();
    @Query(value = "SELECT * FROM product WHERE MATCH(name, description) AGAINST(:searchText IN BOOLEAN MODE)", nativeQuery = true)
    List<Product> searchByText(@Param("searchText") String searchText);
    List<Product> findAllById(Iterable<Integer> ids);

}
