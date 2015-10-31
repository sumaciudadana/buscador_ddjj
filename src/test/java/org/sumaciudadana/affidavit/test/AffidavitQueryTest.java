package org.sumaciudadana.affidavit.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.sumaciudadana.affidavit.entity.Affidavit;
import org.sumaciudadana.affidavit.entity.Pservant;
import org.sumaciudadana.affidavit.service.AffidavitService;
import org.sumaciudadana.affidavit.service.PServantService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class AffidavitQueryTest {

	@Autowired
	AffidavitService affidavitService;
	@Autowired
	PServantService pservantService;

	@Test
	public void testQueryServantById() {
		Pservant pservant = pservantService.getServantById(1);
		Assert.assertNotNull(pservant.getSerName());
	}

	@Test
	public void testDni() {
		Pservant pservant = pservantService.getServantById(1);
		Assert.assertNotNull(pservant.getSerName());
		Assert.assertTrue(pservant.getSerDni() != null);
	}

	@Test
	public void testQuery() {
		Pservant selectedServant = new Pservant();
		selectedServant.setIdpservant(23);

		selectedServant = pservantService.getServantById(selectedServant
				.getIdpservant());

		List<Affidavit> queryresult = affidavitService.getAffidavitQuery(
				selectedServant.getIdpservant(), 0, 0, 0, 0, 0);
		
		for (Affidavit affidavit : queryresult) {
			System.out.println(affidavit.getIdaffidavit()+" "+affidavit.getAffiType());
		}

	}

}
