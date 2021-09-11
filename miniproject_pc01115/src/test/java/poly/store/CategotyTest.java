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
import poly.store.entity.Category;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CategotyTest {
	@Autowired 
	CategoryDAO cdao;
		
	@Test
	@Order(1)
	public void testCreate() {
		Category category1 = new Category();
		category1.setId(1001);
		category1.setName("Kim Cương");		
		String befoSave = category1.getId()+category1.getName();		
		cdao.save(category1);
		Category category2 = cdao.findById(1001).get();
		String afterSave = category2.getId()+category2.getName();				
		Assertions.assertEquals(befoSave, afterSave);
	}
	
	@Test
	@Order(2)
	public void testReadAll() {
		List<Category> categorys = cdao.findAll();
		assertThat(categorys).size().isGreaterThan(0);
	}
	
	@Test
	@Order(3)
	public void testUpdate() {
		Category category1 = new Category();
		category1.setId(1001);
		category1.setName("Nhẫn");		
		String befoUpdate = category1.getId()+category1.getName();		
		cdao.save(category1);
		Category category2 = cdao.findById(1001).get();
		String afterUpdate = category2.getId()+category2.getName();				
		Assertions.assertEquals(befoUpdate, afterUpdate);
	}
	
	@Test
	@Order(4)
	public void testDelete() {		
		cdao.deleteById(1001);		
		assertThat(cdao.existsById(1001)).isFalse();		
	}
	

}
