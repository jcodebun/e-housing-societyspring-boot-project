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

import in.com.ehousing.dto.ComplainDTO;


@Repository
public class ComplainDAOImpl implements ComplainDAOInt {

	private static Logger log = Logger.getLogger(ComplainDAOImpl.class.getName());
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public long add(ComplainDTO dto) {
		log.info("ComplainDAOImpl Add method Start");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long) session.save(dto);
		log.info("ComplainDAOImpl Add method End");
		return pk;
	}

	@Override
	public void delete(ComplainDTO dto) {
		log.info("ComplainDAOImpl Delete method Start");
		entityManager.remove(entityManager.contains(dto) ? dto : entityManager.merge(dto));
		log.info("ComplainDAOImpl Delete method End");
		
	}

	@Override
	public ComplainDTO findBypk(long pk) {
		log.info("ComplainDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		ComplainDTO dto = (ComplainDTO) session.get(ComplainDTO.class, pk);
		log.info("ComplainDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public ComplainDTO findByName(String name) {
		log.info("ComplainDAOImpl FindByLogin method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(ComplainDTO.class);
		criteria.add(Restrictions.eq("subject", name));
		ComplainDTO dto = (ComplainDTO) criteria.uniqueResult();
		log.info("ComplainDAOImpl FindByLogin method End");
		return dto;
	}

	@Override
	public void update(ComplainDTO dto) {
		log.info("ComplainDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		session.merge(dto);
		log.info("ComplainDAOImpl update method End");
	}

	@Override
	public List<ComplainDTO> list() {
		return list(0, 0);
	}

	@Override
	public List<ComplainDTO> list(int pageNo, int pageSize) {
		log.info("ComplainDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<ComplainDTO> query = session.createQuery("from ComplainDTO", ComplainDTO.class);
		List<ComplainDTO> list = query.getResultList();
		log.info("ComplainDAOImpl List method End");
		return list;
	}

	@Override
	public List<ComplainDTO> search(ComplainDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List<ComplainDTO> search(ComplainDTO dto, int pageNo, int pageSize) {
		log.info("ComplainDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		StringBuffer hql = new StringBuffer("from ComplainDTO as u where 1=1 ");
		if (dto != null) {
			if (dto.getId() > 0) {
				hql.append("and u.id = " + dto.getId());
			}
			if (dto.getUserId() > 0) {
				hql.append("and u.userId = " + dto.getUserId());
			}
			if (dto.getSubject() != null && dto.getSubject().length() > 0) {
				hql.append("and u.name like '%" + dto.getSubject() + "%'");
			}
			
			if (dto.getStatus() != null && dto.getStatus().length() > 0) {
				hql.append("and u.status like '%" + dto.getStatus() + "%'");
			}
		}
		Query<ComplainDTO> query = session.createQuery(hql.toString(), ComplainDTO.class);
		if (pageNo > 0) {
			pageNo = (pageNo - 1) * pageSize;
			query.setFirstResult(pageNo);
			query.setMaxResults(pageSize);
		}
		List<ComplainDTO> list = query.getResultList();
		log.info("ComplainDAOImpl Search method End");
		return list;
	}

	/*
	 * @Override public Blob getImageById(long id) throws SerialException,
	 * SQLException { Session session=entityManager.unwrap(Session.class); ComplainDTO
	 * person = (ComplainDTO) session.get(ComplainDTO.class, id); byte[] blob =
	 * person.getImage(); Blob bBlob= new SerialBlob(blob); return bBlob; }
	 */
	

}
