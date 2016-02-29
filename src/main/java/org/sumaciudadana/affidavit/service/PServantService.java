package org.sumaciudadana.affidavit.service;

import java.util.List;
import java.util.SortedSet;

import org.sumaciudadana.affidavit.entity.Pservant;

public interface PServantService {
	
	public List<Pservant> getAllPservants();
	public Pservant getServantById(int pservantid);
	public List<Pservant> getServantsById(SortedSet<Integer> ids);
	public Pservant getServantByFullName(String name, String lastname);
	public List<Pservant> getServantByCategory(int category);
	public List<Pservant> getServantByPosition(int position);
}
