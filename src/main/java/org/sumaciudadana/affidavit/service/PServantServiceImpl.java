package org.sumaciudadana.affidavit.service;

import java.util.List;
import java.util.SortedSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sumaciudadana.affidavit.DAO.PServantDAO;
import org.sumaciudadana.affidavit.entity.Pservant;

@Transactional
@Service
public class PServantServiceImpl implements PServantService {

	@Autowired
	PServantDAO pservantDAO;

	@Override
	public List<Pservant> getAllPservants() {
		return pservantDAO.getAllPservants();
	}

	@Override
	public Pservant getServantById(int pservantid) {
		return pservantDAO.getServantById(pservantid);
	}

	@Override
	public List<Pservant> getServantsById(SortedSet<Integer> ids) {
		return pservantDAO.getServantsById(ids);
	}
	
	public Pservant getServantByFullName(String name, String lastname){
		return pservantDAO.getServantByFullName(name, lastname);
	}

	@Override
	public List<Pservant> getServantByCategory(int category) {
		return pservantDAO.getServantByCategory(category);
	}

//	@Override
//	public List<Pservant> getServantByCatMun(int category, int municipality) {
//		return pservantDAO.getServantByCatMun(category, municipality);
//	}

}
