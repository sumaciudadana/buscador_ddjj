package org.sumaciudadana.affidavit.DAO;

import java.util.List;

import org.sumaciudadana.affidavit.entity.Filelog;
import org.sumaciudadana.affidavit.entity.User;

public interface FileLogDAO {

	public void saveFile(String fileName, String fileUniqueName, User username) throws Exception;
	public List<Filelog> loadFiles() throws Exception;
	public Filelog getFilelog(int id) throws Exception;
}
