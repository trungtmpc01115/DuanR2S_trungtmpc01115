package poly.store.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import poly.store.entity.Role;

public interface RoleService {

	ResponseEntity<List<Role>> findAll();

	ResponseEntity<Role> create(Role role);

	ResponseEntity<Role> update(Role role);

	ResponseEntity<Void> delete(String id);
  
}
