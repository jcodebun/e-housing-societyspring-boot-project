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

import in.com.ehousing.dto.AllocateDTO;

@Repository
public class AllocateDAOImpl implements AllocateDAOInt {

	private static Logger log = Logger.getLogger(AllocateDAOImpl.class.getName());

	@Autowired
	private EntityManager entityManager;

	@Override
	public long add(AllocateDTO dto) {
		log.info("AllocateDAOImpl Add method Start");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long) session.save(dto);
		log.info("AllocateDAOImpl Add method End");
		return pk;
	}

	@Override
	public void delete(AllocateDTO dto) {
		log.info("AllocateDAOImpl Delete method Start");
		entityManager.remove(entityManager.contains(dto) ? dto : entityManager.merge(dto));
		log.info("AllocateDAOImpl Delete method End");

	}

	@Override
	public AllocateDTO findBypk(long pk) {
		log.info("AllocateDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		AllocateDTO dto = (AllocateDTO) session.get(AllocateDTO.class, pk);
		log.info("AllocateDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public AllocateDTO findByEmail(String email) {
		log.info("AllocateDAOImpl FindByLogin method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(AllocateDTO.class);
		criteria.add(Restrictions.eq("email", email));
		AllocateDTO dto = (AllocateDTO) criteria.uniqueResult();
		log.info("AllocateDAOImpl FindByLogin method End");
		return dto;
	}
	
	@Override
	public AllocateDTO findByUserName(String userName) {
		log.info("AllocateDAOImpl findByUserName method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(AllocateDTO.class);
		criteria.add(Restrictions.eq("userName",userName));
		AllocateDTO dto = (AllocateDTO) criteria.uniqueResult();
		log.info("AllocateDAOImpl findByUserName method End");
		return dto;
	}

	@Override
	public void update(AllocateDTO dto) {
		log.info("AllocateDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		session.merge(dto);
		log.info("AllocateDAOImpl update method End");
	}

	@Override
	public List<AllocateDTO> list() {
		return list(0, 0);
	}

	@Override
	public List<AllocateDTO> list(int pageNo, int pageSize) {
		log.info("AllocateDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<AllocateDTO> query = session.createQuery("from AllocateDTO", AllocateDTO.class);
		List<AllocateDTO> list = query.getResultList();
		log.info("AllocateDAOImpl List method End");
		return list;
	}

	@Override
	public List<AllocateDTO> search(AllocateDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List<AllocateDTO> search(AllocateDTO dto, int pageNo, int pageSize) {
		log.info("AllocateDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		StringBuffer hql = new StringBuffer("from AllocateDTO as u where 1=1 ");
		if (dto != null) {
			if (dto.getId() > 0) {
				hql.append("and u.id = " + dto.getId());
			}
			if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {
				hql.append("and u.firstName like '%" + dto.getFirstName() + "%'");
			}
			if (dto.getEmail() != null && dto.getEmail().length() > 0) {
				hql.append("and u.email like '%" + dto.getEmail() + "%'");
			}
			
		}
		Query<AllocateDTO> query = session.createQuery(hql.toString(), AllocateDTO.class);
		if (pageNo > 0) {
			pageNo = (pageNo - 1) * pageSize;
			query.setFirstResult(pageNo);
			query.setMaxResults(pageSize);
		}
		List<AllocateDTO> list = query.getResultList();
		log.info("AllocateDAOImpl Search method End");
		return list;
	}

	@Override
	public Blob getImageById(long id) throws SerialException, SQLException {
		Session session = entityManager.unwrap(Session.class);
		AllocateDTO person = (AllocateDTO) session.get(AllocateDTO.class, id);
		byte[] blob = person.getImage();
		Blob bBlob = new SerialBlob(blob);
		return bBlob;
	}

	

}
