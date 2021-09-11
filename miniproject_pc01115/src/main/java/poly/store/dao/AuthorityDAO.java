package poly.store.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.store.entity.Account;
import poly.store.entity.Authority;
import poly.store.entity.Role;



public interface AuthorityDAO extends JpaRepository<Authority, Integer>{
	@Query("SELECT auth.role FROM Authority auth WHERE auth.account.username=?1")
	List<Role> findRoleByUsername(String username);

}
