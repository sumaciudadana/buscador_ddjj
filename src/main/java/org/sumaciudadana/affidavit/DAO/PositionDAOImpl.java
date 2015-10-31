package org.sumaciudadana.affidavit.DAO;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sumaciudadana.affidavit.entity.Jurisdiction;
import org.sumaciudadana.affidavit.entity.Organization;
import org.sumaciudadana.affidavit.entity.Position;

@Repository
public class PositionDAOImpl implements PositionDAO {
	
	private final static Logger LOGGER = Logger.getLogger(PositionDAOImpl.class
			.getName());

	@Autowired
	private SessionFactory sessionFactory;

	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Organization> getOrganization() {
		List<Organization> list = sessionFactory.getCurrentSession()
				.createQuery("from Organization").list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Jurisdiction> getJurisdiction(Organization org) {
		List<Jurisdiction> result = null;
		int idOrg = org.getIdOrganization();
		try {
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
					Jurisdiction.class);
			if (idOrg != 0) {
				criteria.add(Restrictions.eq("organization.idOrganization", idOrg));
				result = criteria.list();
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Error getting jurisdictions for organization "+org.getIdOrganization(), e);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Position> getPositions(Jurisdiction jurisdiction) {
		List<Position> result = null;
		int jurId = jurisdiction.getIdJurisdiction();
		try {
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
					Position.class);
			if (jurId != 0) {
				criteria.add(Restrictions.eq("jurisdiction.idJurisdiction", jurId));
				result = criteria.list();
			}
			
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Error getting positions for jurisdiction"+jurisdiction.getIdJurisdiction(), e);
		}
		return result;
	}

}
