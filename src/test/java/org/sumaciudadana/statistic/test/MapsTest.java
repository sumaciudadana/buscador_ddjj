package org.sumaciudadana.statistic.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.sumaciudadana.affidavit.entity.Belonging;
import org.sumaciudadana.affidavit.service.BelongingService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class MapsTest {
	
	@Autowired
	BelongingService belongingService;
	
	@Test
	public void testMunicipalityInformation(){
		List<Belonging> belList = belongingService.getAllBelongings();
		
		Assert.assertNotNull(belList);
		Assert.assertTrue(belList.size()>0);
		
		
	}

}
