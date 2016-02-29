package org.sumaciudadana.affidavit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sumaciudadana.affidavit.DAO.AffidavitDAO;
import org.sumaciudadana.affidavit.entity.Affidavit;

@Transactional
@Service
public class AffidavitServiceImpl implements AffidavitService {

	@Autowired
	AffidavitDAO affidavitDAO;

	@Override
	public List<Affidavit> getAffidavitQuery(int pservant, int position,
			long minwealth, long maxwealth, int minperiod, int maxperiod) {
		return affidavitDAO.getAffidavitQuery(pservant, position, minwealth,
				maxwealth, minperiod, maxperiod);
	}

	@Override
	public List<Affidavit> getAffidavitQuery(int pservant) {
		return affidavitDAO.getAffidavitQuery(pservant);
	}

	@Override
	public List<Affidavit> getAffidavitByPosition(int idposition) {
		return affidavitDAO.getAffidavitByPosition(idposition);
	}

}
