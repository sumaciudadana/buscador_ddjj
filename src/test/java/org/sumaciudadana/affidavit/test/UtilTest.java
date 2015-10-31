package org.sumaciudadana.affidavit.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.sumaciudadana.affidavit.entity.Affidavit;
import org.sumaciudadana.affidavit.service.AffidavitService;
import org.sumaciudadana.affidavit.util.AffidavitUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext-test.xml"})
public class UtilTest {
	
	@Autowired
	AffidavitUtils AffidavitUtils;
	
	@Autowired
	AffidavitService affidavitService;
	
	@Test
	public void testYearList(){
		ArrayList<String> years = AffidavitUtils.listOfYears();
		Assert.assertTrue(years.size()>0);
	}
	
	@Test
	public void testChart(){
		List<Affidavit> query = affidavitService.getAffidavitQuery(28, 0, 0, 0, 0, 0);
		String result = AffidavitUtils.getChartData(query);
		System.out.println(result);
	}

}
