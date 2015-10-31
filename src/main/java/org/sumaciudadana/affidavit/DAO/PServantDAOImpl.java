package org.sumaciudadana.affidavit.DAO;

import java.util.List;
import java.util.SortedSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sumaciudadana.affidavit.entity.Pservant;

@Repository
public class PServantDAOImpl implements PServantDAO {

	private static final Logger LOGGER = Logger.getLogger(PServantDAOImpl.class
			.getName());

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Pservant> getAllPservants() {
		List<Pservant> list = sessionFactory.getCurrentSession()
				.createQuery("from Pservant").list();
		return list;
	}

	@Override
	public Pservant getServantById(int pservantid) {
		Session session = sessionFactory.getCurrentSession();
		Pservant pservant = (Pservant) session.get(Pservant.class, pservantid);
		return pservant;
	}

	@SuppressWarnings("unchecked")
	public List<Pservant> getServantsById(SortedSet<Integer> ids) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Pservant.class);
		criteria.add(Restrictions.in("idpservant", ids));
		criteria.addOrder(Order.asc("serFirstsurname"));
		return criteria.list();
	}

	public Pservant getServantByFullName(String name, String lastname) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Pservant.class);
		criteria.add(Restrictions.eq("serName", name.trim()));
		criteria.add(Restrictions.like("serFirstsurname", lastname.trim(),
				MatchMode.START));
		return (Pservant) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pservant> getServantByCategory(int category) {
		List<Pservant> result = null;
		Criteria criteria = sessionFactory.openSession().createCriteria(
				Pservant.class);
		try {
			criteria.add(Restrictions.eq("serCategory.idCategory", category));
			result = criteria.list();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Error listing servants", e);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pservant> getServantByEntity(Pservant pservant) {
		List<Pservant> result = null;
		Criteria criteria = sessionFactory.openSession().createCriteria(
				Pservant.class);
		try {
			result = criteria.list();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Error listing servants", e);
		}
		return result;
	}
}
