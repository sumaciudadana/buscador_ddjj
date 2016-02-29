package org.sumaciudadana.affidavit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sumaciudadana.affidavit.DAO.AffidavitDAO;
import org.sumaciudadana.affidavit.DAO.BelongingDAO;
import org.sumaciudadana.affidavit.entity.Affidavit;
import org.sumaciudadana.affidavit.entity.Belonging;

@Transactional
@Service
public class BelongingServiceImpl implements BelongingService {

	final static String[] PRESENTATIONS = new String[] { "C", "P", "I" };

	@Autowired
	BelongingDAO belongingDAO;

	@Autowired
	AffidavitDAO affidavitDAO;

	@Override
	public List<Belonging> getAllBelongings() {
		return belongingDAO.getAllBelongings();
	}

	@Override
	public String getMapData(int idpservant) {
		Affidavit affi = null;
		int counter = 0;
		StringBuilder data = new StringBuilder();
		do {
			affi = affidavitDAO.getLastAffiByPresentation(
					PRESENTATIONS[counter], idpservant);
			counter++;
		} while (affi == null || counter > PRESENTATIONS.length);

		if (affi != null) {
			List<Belonging> belongings = belongingDAO.getBellongingByAffi(affi
					.getIdaffidavit());
			if (belongings != null) {
				Belonging bel;
				for (int i = 0; i < belongings.size(); i++) {
					bel = belongings.get(i);
					if (i != 0) {
						data.append(";");
					}
					data.append(bel.getCoordinateLat());
					data.append(",");
					data.append(bel.getCoordinateLng());
				}
			}
		}
		return data.toString();
	}

}
