package org.sumaciudadana.affidavit.service;

import java.util.List;

import org.sumaciudadana.affidavit.entity.Jurisdiction;
import org.sumaciudadana.affidavit.entity.Organization;
import org.sumaciudadana.affidavit.entity.Position;

public interface PositionService {

	public List<Organization> getOrganization();
	public List<Position> getPositions(Jurisdiction selectedJurisdiction);
	public List<Jurisdiction> getJurisdictions(Organization org);
	
}
