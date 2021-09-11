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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import poly.store.entity.Authority;
import poly.store.service.AuthorityService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/authorities")
public class AuthorityRestController {
   @Autowired
   AuthorityService authorityService ;
   
   @GetMapping
   public ResponseEntity<List<Authority>> findAll(){
	   return authorityService.findAll();
   }
   
   @PostMapping
   public ResponseEntity<Authority> post(@RequestBody Authority auth) {
	   return authorityService.create(auth);
   }
   
  
   @DeleteMapping("{id}")
   public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
	   return authorityService.delete(id);
   }
   
   @GetMapping("{role}")
   public Boolean isRole(@PathVariable("role") String role){
	   return authorityService.isAdmin(role);
   }
  
}
