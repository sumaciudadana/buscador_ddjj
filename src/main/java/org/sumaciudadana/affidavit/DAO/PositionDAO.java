package org.sumaciudadana.affidavit.DAO;

import java.util.List;

import org.sumaciudadana.affidavit.entity.Jurisdiction;
import org.sumaciudadana.affidavit.entity.Organization;
import org.sumaciudadana.affidavit.entity.Position;

public interface PositionDAO {
	
	public List<Organization> getOrganization();
	public List<Position> getPositions(Jurisdiction jurisdiction);
	public List<Jurisdiction> getJurisdiction(Organization org);

}
