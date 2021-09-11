package poly.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import poly.store.dao.CategoryDAO;
import poly.store.entity.Category;
import poly.store.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryDAO cdao;
	
	@Override
	public ResponseEntity<List<Category>> findAll() {
		return ResponseEntity.ok(cdao.findAll());
	}

	@Override
	public ResponseEntity<Category> create(Category category) {
		if(cdao.existsById(category.getId())) {
			return ResponseEntity.badRequest().build();
		}
		cdao.save(category);
		return ResponseEntity.ok(category);
	}

	@Override
	public ResponseEntity<Category> update(Category category) {
		if(!cdao.existsById(category.getId())) {
			return ResponseEntity.notFound().build();
		}
		cdao.save(category);
		return ResponseEntity.ok(category);
	}

	@Override
	public ResponseEntity<Void> delete(Integer id) {
		if(!cdao.existsById(id)) {
			return ResponseEntity.notFound().build();
		}		
		cdao.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
}
