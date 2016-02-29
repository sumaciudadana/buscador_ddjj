package org.sumaciudadana.affidavit.DAO;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sumaciudadana.affidavit.entity.Filelog;
import org.sumaciudadana.affidavit.entity.User;

@Repository
public class FileLogDAOImpl implements FileLogDAO {
	
	private final static Logger LOGGER = Logger.getLogger(FileLogDAOImpl.class
			.getName());

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveFile(String fileName, String fileUniqueName, User user)
			throws Exception {
		Session session = sessionFactory.openSession();
		Filelog file = new Filelog(user, fileName, fileUniqueName, new Date());
		session.save(file);
		session.flush();
		LOGGER.info("File "+fileName+" saved succesfuly");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Filelog> loadFiles() throws Exception {
		List<Filelog> list = sessionFactory.getCurrentSession()
				.createQuery("from Filelog").list();
		return list;
	}

	@Override
	public Filelog getFilelog(int id) throws Exception {
		Filelog response = null;
		Session session = sessionFactory.getCurrentSession();
		response = (Filelog) session.get(Filelog.class, id);
		return response;
	}

}
