package poly.store.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import poly.store.dao.AccountDAO;
import poly.store.entity.Account;
import poly.store.service.AccountService;

@Service
public class AccountServiceIml implements AccountService {
	@Autowired
	AccountDAO adao;
	@Autowired
	HttpServletRequest req;

	@Override
	public ResponseEntity<Account> findById(String username) {
		Optional<Account> account = adao.findById(username);
		if (!account.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(account.get());
	}

	@Override
	public ResponseEntity<List<Account>> findAll() {
		String usernameLogin = req.getRemoteUser();		
		List<Account> listUser = adao.findAll().stream().filter(user -> !user.getUsername().equals(usernameLogin))
				.collect(Collectors.toList());
		return ResponseEntity.ok(listUser);
	}

	@Override
	public ResponseEntity<Account> create(Account account) {
		if (adao.existsById(account.getUsername())) {
			return ResponseEntity.badRequest().build();
		} else {
			adao.save(account);
			return ResponseEntity.ok(account);
		}
	}

	@Override
	public ResponseEntity<Account> update(Account account) {
		if (!adao.existsById(account.getUsername())) {
			return ResponseEntity.notFound().build();
		}
		adao.save(account);
		return ResponseEntity.ok(account);
	}

	@Override
	public ResponseEntity<Void> delete(String username) {
		if (!adao.existsById(username)) {
			return ResponseEntity.notFound().build();
		} else {
			adao.deleteById(username);
			return ResponseEntity.ok().build();
		}
	}

}
