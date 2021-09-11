package poly.store.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import poly.store.dao.AccountDAO;
import poly.store.dao.AuthorityDAO;
import poly.store.entity.Account;
import poly.store.entity.Authority;
import poly.store.entity.Role;
import poly.store.service.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService{
	@Autowired
	AuthorityDAO dao;

	@Autowired
	AccountDAO adao;
	
	@Autowired
	HttpServletRequest req;

	@Override
	public ResponseEntity<List<Authority>> findAll() {
		return ResponseEntity.ok(dao.findAll());
	}

	@Override
	public ResponseEntity<Authority> create(Authority auth) {
		dao.save(auth);
		return ResponseEntity.ok(auth);
	}

	@Override
	public ResponseEntity<Void> delete(Integer id) {
		if(!dao.existsById(id)) {
			return ResponseEntity.notFound().build();
		}		
		dao.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@Override
	public List<Role> findRoleByUsername(String username) {
		return dao.findRoleByUsername(username);
	}

	@Override
	public Boolean isAdmin(String role) {
		String usernameLogin = req.getRemoteUser();	
		List<Authority> authoOfUser = dao.findAll().stream().filter(autho->autho.getAccount().getUsername().equals(usernameLogin)).collect(Collectors.toList());
		if(authoOfUser.stream().anyMatch(autho->autho.getRole().getId().equalsIgnoreCase(role))) {
			return true;
		}
		return false;
	}	
} 
