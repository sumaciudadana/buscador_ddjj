package org.sumaciudadana.affidavit.DAO;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sumaciudadana.affidavit.entity.User;

@Repository
public class LoginDAOImpl implements LoginDAO {

	private final static Logger LOGGER = Logger.getLogger(LoginDAOImpl.class
			.getName());

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean validateCredentials(String username, String pwd) {

		try {
			User user = null;
			Criteria criteria = sessionFactory.getCurrentSession()
					.createCriteria(User.class);
			criteria.add(Restrictions.eq("username", username));
			criteria.add(Restrictions.eq("password", pwd));
			criteria.add(Restrictions.eq("UStatus", '1'));
			user = (User) criteria.uniqueResult();

			if (user != null) {
				return true;
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Error logging user " + username
					+ " with password " + pwd, e);
		}

		return false;
	}

}
