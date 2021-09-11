package poly.store.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poly.store.entity.Product;
import poly.store.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/products")
public class ProductRestController {
	@Autowired
	ProductService productService;
	
	@GetMapping()
	public ResponseEntity<List<Product>> getALl() {
		return productService.findAll();
	}
	
	
	@PreAuthorize("hasAnyRole('PM','ADMIN')")
	@PostMapping()
	public ResponseEntity<Product> create(@RequestBody Product product) {
		return productService.create(product);
	}
	
	@PreAuthorize("hasAnyRole('PM','ADMIN')")
	@PutMapping()
	public ResponseEntity<Product> update(@RequestBody Product product) {
		return productService.update(product);
	}
	
	@PreAuthorize("hasAnyRole('PM','ADMIN')")
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		 return productService.delete(id);
	}
}
