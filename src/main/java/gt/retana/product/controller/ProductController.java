package gt.retana.product.controller;

import gt.retana.product.model.Product;
import gt.retana.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/product")
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping()
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }
    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
    @GetMapping("/{id}")
    public Product getProduct(@RequestParam("id") Integer id) {
        return productService.getProductById(id);
    }
    @DeleteMapping("/{id}")
    public void deleteProduct(@RequestParam("id") Integer id) {
        productService.deleteProductById(id);
    }
    @GetMapping("/search")
    public List<Product> search(@RequestParam String query) {
        return productService.searchProductos(query);
    }
}
