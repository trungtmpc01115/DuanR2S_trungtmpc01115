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

import poly.store.dao.CategoryDAO;
import poly.store.dao.RoleDAO;
import poly.store.entity.Category;
import poly.store.entity.Role;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RoleTest {
	@Autowired 
	RoleDAO rdao;
		
	@Test
	@Order(1)
	public void testCreate() {
		Role role1 = new Role();
		role1.setId("ADTEST");
		role1.setName("test role");		
		String befoSave = role1.getId()+role1.getName();		
		rdao.save(role1);
		Role role2 = rdao.findById("ADTEST").get();
		String afterSave = role1.getId()+role1.getName();				
		Assertions.assertEquals(befoSave, afterSave);
	}
	
	@Test
	@Order(2)
	public void testReadAll() {
		List<Role> roles = rdao.findAll();
		assertThat(roles).size().isGreaterThan(0);
	}
	
	@Test
	@Order(3)
	public void testUpdate() {
		Role role1 = new Role();
		role1.setId("ADTEST");
		role1.setName("abc1");		
		String befoUpdate = role1.getId()+role1.getName();		
		rdao.save(role1);
		Role role2 = rdao.findById("ADTEST").get();
		String afterUpdate = role2.getId()+role2.getName();				
		Assertions.assertEquals(befoUpdate, afterUpdate);
	}
	
	@Test
	@Order(4)
	public void testDelete() {		
		rdao.deleteById("ADTEST");		
		assertThat(rdao.existsById("ADTEST")).isFalse();		
	}
	

}
