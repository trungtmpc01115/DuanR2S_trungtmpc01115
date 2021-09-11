package poly.store.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import poly.store.entity.Category;

public interface CategoryService {
	ResponseEntity<List<Category>> findAll();

	ResponseEntity<Category> create(Category category);

	ResponseEntity<Category> update(Category category);

	ResponseEntity<Void> delete(Integer id);
}
