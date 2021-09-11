package poly.store.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import poly.store.entity.Product;

public interface ProductService {

	ResponseEntity<List<Product>> findAll();

	ResponseEntity<Product> create(Product product);

	ResponseEntity<Product> update(Product product);

	ResponseEntity<Void> delete(Integer id);

}
