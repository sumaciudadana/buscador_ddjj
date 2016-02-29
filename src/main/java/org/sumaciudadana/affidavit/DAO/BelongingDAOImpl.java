package org.sumaciudadana.affidavit.DAO;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sumaciudadana.affidavit.entity.Belonging;

@Repository
public class BelongingDAOImpl implements BelongingDAO {

	private static final Logger LOGGER = Logger.getLogger(Belonging.class
			.getName());

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Belonging> getMaxBelong(int amount, int idpservant) {
		List<Belonging> belongs;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Belonging.class);
		criteria.addOrder(Order.desc("valueBelonging"));

		if (amount != 0) {
			criteria.setMaxResults(amount);
		}

		if (idpservant != 0) {
			criteria.createAlias("affidavit", "affi");
			criteria.createAlias("affi.pservant", "pser");
			criteria.add(Restrictions.eq("pser.idpservant", idpservant));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		}

		criteria.add(Restrictions.ne("categoryBelonging", "O"));
		belongs = criteria.list();
		return belongs;
	}

	@Override
	public Belonging getMaxBelong(int idpservant) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Belonging.class);
		Belonging response;

		criteria.createAlias("affidavit", "affi");
		criteria.createAlias("affi.pservant", "pser");
		criteria.add(Restrictions.eq("pser.idpservant", idpservant));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.add(Restrictions.ne("categoryBelonging", "O"));
		criteria.addOrder(Order.desc("valueBelonging"));
		criteria.setMaxResults(1);

		response = (Belonging) criteria.uniqueResult();
		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Belonging> getAllBelongings() {
		List<Belonging> belongings = sessionFactory.getCurrentSession()
				.createQuery("from Belonging").list();
		return belongings;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Belonging> getBellongingByAffi(int idAffidavit) {
		List<Belonging> result = null;
		try {
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
					Belonging.class);
			criteria.add(Restrictions.eq("affidavit.idaffidavit",idAffidavit));
			criteria.add(Restrictions.isNotNull("coordinateLat"));
			criteria.add(Restrictions.isNotNull("coordinateLng"));
			result = criteria.list();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Error loading belongings",e);
		}
		return result;
	}

}
