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

import in.com.ehousing.dto.SocietyDTO;

@Repository
public class SocietyDAOImpl implements SocietyDAOInt {

	private static Logger log = Logger.getLogger(SocietyDAOImpl.class.getName());

	@Autowired
	private EntityManager entityManager;

	@Override
	public long add(SocietyDTO dto) {
		log.info("SocietyDAOImpl Add method Start");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long) session.save(dto);
		log.info("SocietyDAOImpl Add method End");
		return pk;
	}

	@Override
	public void delete(SocietyDTO dto) {
		log.info("SocietyDAOImpl Delete method Start");
		entityManager.remove(entityManager.contains(dto) ? dto : entityManager.merge(dto));
		log.info("SocietyDAOImpl Delete method End");

	}

	@Override
	public SocietyDTO findBypk(long pk) {
		log.info("SocietyDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		SocietyDTO dto = (SocietyDTO) session.get(SocietyDTO.class, pk);
		log.info("SocietyDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public SocietyDTO findByName(String name) {
		log.info("SocietyDAOImpl FindByLogin method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(SocietyDTO.class);
		criteria.add(Restrictions.eq("name", name));
		SocietyDTO dto = (SocietyDTO) criteria.uniqueResult();
		log.info("SocietyDAOImpl FindByLogin method End");
		return dto;
	}

	@Override
	public void update(SocietyDTO dto) {
		log.info("SocietyDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		session.merge(dto);
		log.info("SocietyDAOImpl update method End");
	}

	@Override
	public List<SocietyDTO> list() {
		return list(0, 0);
	}

	@Override
	public List<SocietyDTO> list(int pageNo, int pageSize) {
		log.info("SocietyDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<SocietyDTO> query = session.createQuery("from SocietyDTO", SocietyDTO.class);
		List<SocietyDTO> list = query.getResultList();
		log.info("SocietyDAOImpl List method End");
		return list;
	}

	@Override
	public List<SocietyDTO> search(SocietyDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List<SocietyDTO> search(SocietyDTO dto, int pageNo, int pageSize) {
		log.info("SocietyDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		StringBuffer hql = new StringBuffer("from SocietyDTO as u where 1=1 ");
		if (dto != null) {
			if (dto.getId() > 0) {
				hql.append("and u.id = " + dto.getId());
			}
			if (dto.getName() != null && dto.getName().length() > 0) {
				hql.append("and u.name like '%" + dto.getName() + "%'");
			}

			if (dto.getCity() != null && dto.getCity().length() > 0) {
				hql.append("and u.city like '%" + dto.getCity() + "%'");
			}
		}
		Query<SocietyDTO> query = session.createQuery(hql.toString(), SocietyDTO.class);
		if (pageNo > 0) {
			pageNo = (pageNo - 1) * pageSize;
			query.setFirstResult(pageNo);
			query.setMaxResults(pageSize);
		}
		List<SocietyDTO> list = query.getResultList();
		log.info("SocietyDAOImpl Search method End");
		return list;
	}

	@Override
	public Blob getImageById(long id) throws SerialException, SQLException {
		Session session = entityManager.unwrap(Session.class);
		SocietyDTO person = (SocietyDTO) session.get(SocietyDTO.class, id);
		byte[] blob = person.getImage();
		Blob bBlob = new SerialBlob(blob);
		return bBlob;
	}

}
