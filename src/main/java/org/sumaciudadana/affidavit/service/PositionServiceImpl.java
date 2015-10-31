package org.sumaciudadana.affidavit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sumaciudadana.affidavit.DAO.PositionDAO;
import org.sumaciudadana.affidavit.entity.Jurisdiction;
import org.sumaciudadana.affidavit.entity.Organization;
import org.sumaciudadana.affidavit.entity.Position;

@Transactional
@Service
public class PositionServiceImpl implements PositionService {

	@Autowired
	PositionDAO positionDAO;

	@Override
	public List<Organization> getOrganization() {
		return positionDAO.getOrganization();
	}

	@Override
	public List<Jurisdiction> getJurisdictions(Organization org) {
		return positionDAO.getJurisdiction(org);
	}

	@Override
	public List<Position> getPositions(Jurisdiction selectedJurisdiction) {
		return positionDAO.getPositions(selectedJurisdiction);
	}

}
