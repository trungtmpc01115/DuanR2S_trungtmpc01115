package poly.store.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import poly.store.entity.Account;
import poly.store.entity.Product;

public interface AccountService {
	ResponseEntity<Account> findById(String username);

	ResponseEntity<List<Account>> findAll();

	ResponseEntity<Account> create(Account account);

	ResponseEntity<Account> update(Account account);

	ResponseEntity<Void> delete(String username);

}
