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

import in.com.ehousing.dto.MessageDTO;


@Repository
public class MessageDAOImpl implements MessageDAOInt {

	private static Logger log = Logger.getLogger(MessageDAOImpl.class.getName());
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public long add(MessageDTO dto) {
		log.info("MessageDAOImpl Add method Start");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long) session.save(dto);
		log.info("MessageDAOImpl Add method End");
		return pk;
	}

	@Override
	public void delete(MessageDTO dto) {
		log.info("MessageDAOImpl Delete method Start");
		entityManager.remove(entityManager.contains(dto) ? dto : entityManager.merge(dto));
		log.info("MessageDAOImpl Delete method End");
		
	}

	@Override
	public MessageDTO findBypk(long pk) {
		log.info("MessageDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		MessageDTO dto = (MessageDTO) session.get(MessageDTO.class, pk);
		log.info("MessageDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public MessageDTO findByName(String name) {
		log.info("MessageDAOImpl FindByLogin method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(MessageDTO.class);
		criteria.add(Restrictions.eq("name", name));
		MessageDTO dto = (MessageDTO) criteria.uniqueResult();
		log.info("MessageDAOImpl FindByLogin method End");
		return dto;
	}

	@Override
	public void update(MessageDTO dto) {
		log.info("MessageDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		session.merge(dto);
		log.info("MessageDAOImpl update method End");
	}

	@Override
	public List<MessageDTO> list() {
		return list(0, 0);
	}

	@Override
	public List<MessageDTO> list(int pageNo, int pageSize) {
		log.info("MessageDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<MessageDTO> query = session.createQuery("from MessageDTO", MessageDTO.class);
		List<MessageDTO> list = query.getResultList();
		log.info("MessageDAOImpl List method End");
		return list;
	}

	@Override
	public List<MessageDTO> search(MessageDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List<MessageDTO> search(MessageDTO dto, int pageNo, int pageSize) {
		log.info("MessageDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		StringBuffer hql = new StringBuffer("from MessageDTO as u where 1=1 ");
		if (dto != null) {
			if (dto.getId() > 0) {
				hql.append("and u.id = " + dto.getId());
			}
			if (dto.getAllocateId() > 0) {
				hql.append("and u.allocateId = " + dto.getAllocateId());
			}
			if (dto.getSubject() != null && dto.getSubject().length() > 0) {
				hql.append("and u.name like '%" + dto.getSubject() + "%'");
			}
		}
		Query<MessageDTO> query = session.createQuery(hql.toString(), MessageDTO.class);
		if (pageNo > 0) {
			pageNo = (pageNo - 1) * pageSize;
			query.setFirstResult(pageNo);
			query.setMaxResults(pageSize);
		}
		List<MessageDTO> list = query.getResultList();
		log.info("MessageDAOImpl Search method End");
		return list;
	}

	/*
	 * @Override public Blob getImageById(long id) throws SerialException,
	 * SQLException { Session session=entityManager.unwrap(Session.class); MessageDTO
	 * person = (MessageDTO) session.get(MessageDTO.class, id); byte[] blob =
	 * person.getImage(); Blob bBlob= new SerialBlob(blob); return bBlob; }
	 */
	

}
