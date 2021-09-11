package poly.store.restcontroller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import poly.store.entity.Account;
import poly.store.entity.Product;
import poly.store.service.AccountService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/accounts")
public class AccountRestController {
	@Autowired
	AccountService accountService;
	
	@GetMapping
	public ResponseEntity<List<Account>>findAll(){
		return accountService.findAll();
	}
	
	
	@PostMapping()
	public ResponseEntity<Account> create(@RequestBody Account account) {
		return accountService.create(account);
	}

	@PutMapping()
	public ResponseEntity<Account> update(@RequestBody Account account) {
		return accountService.update(account);
	}

	@DeleteMapping("{username}")
	public ResponseEntity<Void> delete(@PathVariable("username") String username) {
		return accountService.delete(username);
	}

}
