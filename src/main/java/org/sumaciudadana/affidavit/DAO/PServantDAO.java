package org.sumaciudadana.affidavit.DAO;

import java.util.List;
import java.util.SortedSet;

import org.sumaciudadana.affidavit.entity.Pservant;


public interface PServantDAO {
	
	public List<Pservant> getAllPservants();
	public Pservant getServantById(int pservantid);
	public List<Pservant> getServantsById(SortedSet<Integer> ids);
	public Pservant getServantByFullName(String name, String lastname);
	public List<Pservant> getServantByCategory(int category);
	public List<Pservant> getServantByEntity(Pservant pservant);
	public List<Pservant> getServantByPosition(int position);

}
