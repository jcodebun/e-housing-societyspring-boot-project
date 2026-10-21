package in.com.ehousing.dao;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.com.ehousing.dto.HouseDTO;


@Repository
public class HouseDAOImpl implements HouseDAOInt {

	private static Logger log = Logger.getLogger(HouseDAOImpl.class.getName());
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public long add(HouseDTO dto) {
		log.info("HouseDAOImpl Add method Start");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long) session.save(dto);
		log.info("HouseDAOImpl Add method End");
		return pk;
	}

	@Override
	public void delete(HouseDTO dto) {
		log.info("HouseDAOImpl Delete method Start");
		entityManager.remove(entityManager.contains(dto) ? dto : entityManager.merge(dto));
		log.info("HouseDAOImpl Delete method End");
		
	}

	@Override
	public HouseDTO findBypk(long pk) {
		log.info("HouseDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		HouseDTO dto = (HouseDTO) session.get(HouseDTO.class, pk);
		log.info("HouseDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public HouseDTO findByName(String name) {
		log.info("HouseDAOImpl FindByLogin method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(HouseDTO.class);
		criteria.add(Restrictions.eq("name", name));
		HouseDTO dto = (HouseDTO) criteria.uniqueResult();
		log.info("HouseDAOImpl FindByLogin method End");
		return dto;
	}

	@Override
	public void update(HouseDTO dto) {
		log.info("HouseDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		session.merge(dto);
		log.info("HouseDAOImpl update method End");
	}

	@Override
	public List<HouseDTO> list() {
		return list(0, 0);
	}

	@Override
	public List<HouseDTO> list(int pageNo, int pageSize) {
		log.info("HouseDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<HouseDTO> query = session.createQuery("from HouseDTO", HouseDTO.class);
		List<HouseDTO> list = query.getResultList();
		log.info("HouseDAOImpl List method End");
		return list;
	}

	@Override
	public List<HouseDTO> search(HouseDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List<HouseDTO> search(HouseDTO dto, int pageNo, int pageSize) {
		log.info("HouseDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		StringBuffer hql = new StringBuffer("from HouseDTO as u where 1=1 ");
		if (dto != null) {
			if (dto.getId() > 0) {
				hql.append("and u.id = " + dto.getId());
			}
			if (dto.getName() != null && dto.getName().length() > 0) {
				hql.append("and u.name like '%" + dto.getName() + "%'");
			}
		}
		Query<HouseDTO> query = session.createQuery(hql.toString(), HouseDTO.class);
		if (pageNo > 0) {
			pageNo = (pageNo - 1) * pageSize;
			query.setFirstResult(pageNo);
			query.setMaxResults(pageSize);
		}
		List<HouseDTO> list = query.getResultList();
		log.info("HouseDAOImpl Search method End");
		return list;
	}

	/*
	 * @Override public Blob getImageById(long id) throws SerialException,
	 * SQLException { Session session=entityManager.unwrap(Session.class); HouseDTO
	 * person = (HouseDTO) session.get(HouseDTO.class, id); byte[] blob =
	 * person.getImage(); Blob bBlob= new SerialBlob(blob); return bBlob; }
	 */
	

}
