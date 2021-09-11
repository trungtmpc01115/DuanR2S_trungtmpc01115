package poly.store.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import poly.store.dao.ProductDAO;
import poly.store.entity.Product;
import poly.store.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDAO pdao;

	@Override
	public ResponseEntity<List<Product>> findAll() {
		return ResponseEntity.ok(pdao.findAll());
	}

	@Override
	public ResponseEntity<Product> create(Product product) {	
		if(product.getId()!=null) {
			if(pdao.existsById(product.getId())) {
				return ResponseEntity.badRequest().build();
			}
		}	
		pdao.save(product);
		return ResponseEntity.ok(product);
	}

	@Override
	public ResponseEntity<Product> update(Product product) {
		if(product.getId()==null||!pdao.existsById(product.getId())) {
			return ResponseEntity.notFound().build();
		}
		pdao.save(product);
		return ResponseEntity.ok(product);
	}

	@Override
	public ResponseEntity<Void> delete(Integer id) {
		if(!pdao.existsById(id)) {
			return ResponseEntity.notFound().build();
		}		
		pdao.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
