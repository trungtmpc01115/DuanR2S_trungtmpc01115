package poly.store.restcontroller;

import java.util.List;

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

import poly.store.entity.Category;
import poly.store.entity.Role;
import poly.store.service.RoleService;


@CrossOrigin("*")
@RestController
@RequestMapping("/rest/roles")
public class RoleRestController {
    @Autowired
    RoleService roleService;
    
    @GetMapping
    public ResponseEntity<List<Role>> getAll(){
    	return roleService.findAll();
    }
    
    @PostMapping()
	public ResponseEntity<Role> create(@RequestBody Role role) {
		return roleService.create(role);
	}
	
	@PutMapping()
	public  ResponseEntity<Role> update(@RequestBody Role role) {
		return roleService.update(role);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") String id) {
		return roleService.delete(id);
	}
}
