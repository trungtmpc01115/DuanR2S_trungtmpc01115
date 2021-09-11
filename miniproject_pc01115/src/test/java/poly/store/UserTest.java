package poly.store;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import poly.store.dao.AccountDAO;
import poly.store.dao.CategoryDAO;
import poly.store.entity.Account;
import poly.store.entity.Category;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserTest {
	@Autowired 
	AccountDAO adao;
		
	@Test
	@Order(1)
	public void testCreate() {
		Account account1 = new Account();
		account1.setUsername("usertest");
		account1.setPassword("123");
		account1.setFullname("Nguyen Van NN");
		account1.setEmail("nguyenvan@gmail.com");
		account1.setPhoto("abccc.png");
			
		String befoSave = account1.getUsername()+account1.getPassword()+account1.getFullname()+account1.getEmail()+account1.getPhoto();
		adao.save(account1);
		
		Account account2 = adao.findById("usertest").get();
		String afterSave = account2.getUsername()+account2.getPassword()+account2.getFullname()+account2.getEmail()+account2.getPhoto();				
		Assertions.assertEquals(befoSave, afterSave);
	}
	
	@Test
	@Order(2)
	public void testReadAll() {
		List<Account> accounts = adao.findAll();
		assertThat(accounts).size().isGreaterThan(0);
	}
	
	@Test
	@Order(3)
	public void testUpdate() {
		Account account1 = new Account();	
		account1.setUsername("usertest");
		account1.setPassword("123");
		account1.setFullname("Nguyen Van B");
		account1.setEmail("nguyenvab@gmail.com");
		account1.setPhoto("amhhs.png");
		
		String befoUpdate = account1.getUsername()+account1.getPassword()+account1.getFullname()+account1.getEmail()+account1.getPhoto();
		adao.save(account1);
		
		Account account2 = adao.findById("usertest").get();
		String afterUpdate = account2.getUsername()+account2.getPassword()+account2.getFullname()+account2.getEmail()+account2.getPhoto();				
			
		Assertions.assertEquals(befoUpdate, afterUpdate);
	}
	
	@Test
	@Order(4)
	public void testDelete() {		
		adao.deleteById("usertest");		
		assertThat(adao.existsById("usertest")).isFalse();		
	}
	
	

}
