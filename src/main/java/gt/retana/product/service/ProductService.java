package gt.retana.product.service;

import gt.retana.product.model.Product;
import gt.retana.product.model.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public List<Product> getAllProducts() {
        return  productRepository.findAll();
    }
    public Product getProductById(Integer id) {
        return productRepository.findById(id).orElseThrow();
    }
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
    public Product deleteProductById(Integer id) {
        Product product = productRepository.findById(id).orElseThrow();
        productRepository.delete(product);
        return product;
    }
    public List<Product> searchProductos(String searchText) {
        return productRepository.searchByText(searchText);
    }
    public List<Product> findProductById(List<Integer> productIdList) {
        return productRepository.findAllById(productIdList);
    }
}
