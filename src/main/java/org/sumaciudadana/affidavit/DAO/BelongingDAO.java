package org.sumaciudadana.affidavit.DAO;

import java.util.List;

import org.sumaciudadana.affidavit.entity.Belonging;

public interface BelongingDAO {

	public List<Belonging> getMaxBelong(int amount, int integer);
	public Belonging getMaxBelong(int idpservant);
	public List<Belonging> getAllBelongings();
	public List<Belonging> getBellongingByAffi(int idAffidavit);

}
