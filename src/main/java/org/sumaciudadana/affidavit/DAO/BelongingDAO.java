package org.sumaciudadana.affidavit.DAO;

import java.util.List;

import org.sumaciudadana.affidavit.entity.Belonging;

public interface BelongingDAO {

	List<Belonging> getMaxBelong(int amount, int integer);
	Belonging getMaxBelong(int idpservant);
	List<Belonging> getAllBelongings();

}
