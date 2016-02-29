package org.sumaciudadana.affidavit.DAO;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sumaciudadana.affidavit.entity.Affidavit;

@Repository
public class AffidavitDAOImpl implements AffidavitDAO {
	
	private static final Logger LOGGER = Logger.getLogger(AffidavitDAOImpl.class
			.getName());

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@SuppressWarnings({ "unchecked" })
	public List<Affidavit> getAffidavitQuery(int pservant, int idposition,
			long minwealth, long maxwealth, int minperiod, int maxperiod) {

		List<Affidavit> affidavits;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Affidavit.class);

		if (pservant != 0) {
			criteria.add(Restrictions.eq("pservant.idpservant", pservant));
		}

		if (minperiod != 0 && maxperiod != 0) {
			criteria.add(Restrictions.between("affiYear", minperiod, maxperiod));
		}

		if (maxwealth == 0) {
			criteria.add(Restrictions.ge("affiTotalWealth", minwealth));
		} else {
			criteria.add(Restrictions.between("affiTotalWealth", minwealth,
					maxwealth));
		}

		if (idposition != 0) {
			criteria.add(Restrictions.eq("position.idposition", idposition));
		}

		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.addOrder(Order.desc("affiYear"));

		affidavits = criteria.list();
		return affidavits;
	}

	public Integer getMaxYear(int pservant, String type) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Affidavit.class);

		criteria.add(Restrictions.eq("pservant.idpservant", pservant));
		criteria.add(Restrictions.isNotNull("affiTotalMonthWealth"));
		criteria.add(Restrictions.eq("affiValid", 1));

		// Projections
		ProjectionList proList = Projections.projectionList();

		if (type.equals("max")) {
			proList.add(Projections.max("affiYear"));
		} else if (type.equals("min")) {
			criteria.add(Restrictions.ne("affiTotalMonthWealth", 0L));
			proList.add(Projections.min("affiYear"));
		}

		criteria.setProjection(proList);

		// If result equals null there is no matching affidavit
		if (criteria.uniqueResult() != null) {
			return (Integer) criteria.uniqueResult();
		} else {
			return null;
		}
	}

	public Integer getMaxYearPat(int pservant, String type) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Affidavit.class);

		criteria.add(Restrictions.eq("pservant.idpservant", pservant));
		criteria.add(Restrictions.isNotNull("affiTotalBelong"));
		criteria.add(Restrictions.eq("affiValid", 1));

		// Projections
		ProjectionList proList = Projections.projectionList();

		if (type.equals("max")) {
			proList.add(Projections.max("affiYear"));
		} else if (type.equals("min")) {
			criteria.add(Restrictions.ne("affiTotalBelong", 0L));
			proList.add(Projections.min("affiYear"));
		}

		criteria.setProjection(proList);

		// If result equals null there is no matching affidavit
		if (criteria.uniqueResult() != null) {
			return (Integer) criteria.uniqueResult();
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public Affidavit getMaxWealthAffi(int year, int idpservant) {

		Affidavit result = null;
		List<Affidavit> resultList;
		Long maxWealth = 0L;
		Long temp;

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Affidavit.class);
		criteria.add(Restrictions.eq("pservant.idpservant", idpservant));
		criteria.add(Restrictions.eq("affiYear", year));
		criteria.add(Restrictions.eq("affiValid", 1));
		resultList = criteria.list();

		for (Affidavit affidavit : resultList) {
			temp = affidavit.getAffiTotalMonthWealth();
			if (temp >= maxWealth) {
				maxWealth = temp;
				result = affidavit;
			}
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public Affidavit getMaxPatrimonyhAffi(int year, int idpservant) {

		Affidavit result = null;
		List<Affidavit> resultList;
		Long maxBelong = 0L;
		Long temp;

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Affidavit.class);
		criteria.add(Restrictions.eq("pservant.idpservant", idpservant));
		criteria.add(Restrictions.eq("affiYear", year));
		criteria.add(Restrictions.eq("affiValid", 1));
		resultList = criteria.list();

		for (Affidavit affidavit : resultList) {
			temp = affidavit.getAffiTotalBelong();
			if (temp >= maxBelong) {
				maxBelong = temp;
				result = affidavit;
			}
		}

		return result;
	}

	public long getInvalidAffi(int idpservant) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Affidavit.class);
		criteria.add(Restrictions.eq("pservant.idpservant", idpservant));
		criteria.add(Restrictions.eq("affiValid", 0));

		// Projections
		ProjectionList proList = Projections.projectionList();
		proList.add(Projections.count("affiValid"));
		criteria.setProjection(proList);

		return (Long) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Affidavit> getAffidavitQuery(int pservant) {
		List<Affidavit> affidavits;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Affidavit.class);
		if (pservant != 0) {
			criteria.add(Restrictions.eq("pservant.idpservant", pservant));
		}
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.addOrder(Order.asc("affiYear"));
		affidavits = criteria.list();
		return affidavits;
	}
	
	@SuppressWarnings("unchecked")
	public List<Affidavit> getAffidavitByPosition(int idposition){
		LOGGER.info("parameter position: "+idposition);
		List<Affidavit> response = null;
		try {
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
					Affidavit.class);
			if (idposition!=0) {
				criteria.add(Restrictions.eq("position.idposition", idposition));
			}
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			response = criteria.list();
			LOGGER.info("response size: "+response.size());
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Error listing servants", e);
		}
		return response;
	}

	@Override
	public Affidavit getLastAffiByPresentation(String presentation, int pservant) {
		Affidavit result = null;
		try {
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
					Affidavit.class);
			criteria.add(Restrictions.eq("affiPresentation", presentation));
			criteria.add(Restrictions.eq("pservant.idpservant", pservant));
			criteria.addOrder(Order.desc("affiYear"));
			criteria.setMaxResults(1);
			result = (Affidavit) criteria.uniqueResult();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "error loading affidavit by presentation",e);
		}
		return result;
	}

}
