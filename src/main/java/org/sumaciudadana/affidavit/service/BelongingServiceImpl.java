package org.sumaciudadana.affidavit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sumaciudadana.affidavit.DAO.BelongingDAO;
import org.sumaciudadana.affidavit.entity.Belonging;

@Transactional
@Service
public class BelongingServiceImpl implements BelongingService {
	
	@Autowired
	BelongingDAO belongingDAO;

	@Override
	public List<Belonging> getAllBelongings() {
		return belongingDAO.getAllBelongings();
	}

}
