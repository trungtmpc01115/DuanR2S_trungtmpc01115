package poly.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import poly.store.dao.RoleDAO;
import poly.store.entity.Role;
import poly.store.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    RoleDAO dao;

	@Override
	public ResponseEntity<List<Role>> findAll() {
		return ResponseEntity.ok(dao.findAll());
	}

	@Override
	public ResponseEntity<Role> create(Role role) {
		if(dao.existsById(role.getId())) {
			return ResponseEntity.badRequest().build();
		}
		dao.save(role);
		return ResponseEntity.ok(role);
	}

	@Override
	public ResponseEntity<Role> update(Role role) {
		if(!dao.existsById(role.getId())) {
			return ResponseEntity.notFound().build();
		}
		dao.save(role);
		return ResponseEntity.ok(role);
	}

	@Override
	public ResponseEntity<Void> delete(String id) {
		if(!dao.existsById(id)) {
			return ResponseEntity.notFound().build();
		}		
		dao.deleteById(id);
		return ResponseEntity.ok().build();
	}
    
    
}
