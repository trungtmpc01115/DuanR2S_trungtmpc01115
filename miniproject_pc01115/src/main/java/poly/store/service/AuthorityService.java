package poly.store.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import poly.store.entity.Authority;
import poly.store.entity.Role;

public interface AuthorityService {
	
	ResponseEntity<List<Authority>> findAll();

	ResponseEntity<Authority> create(Authority auth);

	ResponseEntity<Void> delete(Integer id);

	List<Role> findRoleByUsername(String username);

	Boolean isAdmin(String role);

}
