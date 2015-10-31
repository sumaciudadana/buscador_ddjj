package org.sumaciudadana.statistic.test;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.sumaciudadana.affidavit.entity.Affidavit;
import org.sumaciudadana.affidavit.entity.Belonging;
import org.sumaciudadana.affidavit.entity.Pservant;
import org.sumaciudadana.affidavit.service.AffidavitService;
import org.sumaciudadana.affidavit.service.StatisticService;
import org.sumaciudadana.statistic.object.AffidavitFault;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class StatisticTest {

	@Autowired
	AffidavitService affidavitService;
	@Autowired
	StatisticService statisticService;

	@Test
	public void testFaultPorcent() {
		Pservant selected = new Pservant();
		selected.setIdpservant(82);
		List<AffidavitFault> resp = affidavitFailures(selected);
		assertNotNull(resp);

		for (AffidavitFault affidavitFault : resp) {
			// System.out.println(affidavitFault.getFaultPorcent());
		}
	}

	private List<AffidavitFault> affidavitFailures(Pservant selected) {
		Map<Integer, AffidavitFault> affiFaultMap = new HashMap<Integer, AffidavitFault>();

		List<Affidavit> resp = affidavitService.getAffidavitQuery(
				selected.getIdpservant(), 0, 0, 0, 0, 0);

		for (Affidavit affidavit : resp) {
			Integer id = affidavit.getPservant().getIdpservant();
			AffidavitFault fault = (AffidavitFault) affiFaultMap.get(id);

			if (fault == null) {
				fault = new AffidavitFault(affidavit.getPservant());
			}

			fault.setValid(affidavit.getAffiValid());
			affiFaultMap.put(id, fault);
		}

		return new ArrayList<AffidavitFault>(affiFaultMap.values());
	}

	@Test
	public void testOneServant() {
		int pservant = 73;
		Affidavit resp = statisticService.getIncomeIncrement(pservant, 0);

		Assert.assertNotNull(resp);
		Assert.assertNotNull(resp.getIncomeIncrement());


	}

	@Test
	public void testAllServant() {
		int pservant = 0;
		Affidavit resp = statisticService.getIncomeIncrement(pservant, 0);
	}

	@Test
	public void testBelongs() {
		Integer amount = 0;

		List<Belonging> resp = statisticService.getMaxBelongs(amount, null);

		Assert.assertNotNull(resp);
		Assert.assertTrue(resp.size() > 0);

		for (Belonging belonging : resp) {
			if (true) {
				System.out.println(belonging.getBelongingType()
						.getDesBelongingType()
						+ " "
						+ belonging.getValueBelonging()
						+ " "
						+ belonging.getAffidavit().getAffiYear()
						+ " "
						+ belonging.getAffidavit().getAffiPresentation()
						+ " "
						+ belonging.getAffidavit().getIdaffidavit()
						+ " "
						+ belonging.getIdbelonging());
			}
		}

	}

	@Test
	public void testBelongsPservant() {
		Integer amount = 0;

		Pservant pservant = new Pservant();
		pservant.setIdpservant(82);
		List<Belonging> resp = statisticService.getMaxBelongs(amount, pservant);

		Assert.assertNotNull(resp);
		Assert.assertTrue(resp.size() > 0);
	}

	@Test
	public void testunprovidedAffidavit() {
		Map<String, Number> resp = statisticService.getUnprovidedAffidavit();
		Assert.assertNotNull(resp);
		// System.out.println(resp.toString());
	}

}
