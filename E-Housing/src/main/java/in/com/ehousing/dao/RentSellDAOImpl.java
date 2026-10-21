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
import in.com.ehousing.dto.RentSellDTO;


@Repository
public class RentSellDAOImpl implements RentSellDAOInt {

	private static Logger log = Logger.getLogger(RentSellDAOImpl.class.getName());
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public long add(RentSellDTO dto) {
		log.info("RentDAOImpl Add method Start");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long) session.save(dto);
		log.info("RentDAOImpl Add method End");
		return pk;
	}

	@Override
	public void delete(RentSellDTO dto) {
		log.info("RentDAOImpl Delete method Start");
		entityManager.remove(entityManager.contains(dto) ? dto : entityManager.merge(dto));
		log.info("RentDAOImpl Delete method End");
		
	}

	@Override
	public RentSellDTO findBypk(long pk) {
		log.info("RentDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		RentSellDTO dto = (RentSellDTO) session.get(RentSellDTO.class, pk);
		log.info("RentDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public RentSellDTO findByName(String name) {
		log.info("RentDAOImpl FindByLogin method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(RentSellDTO.class);
		criteria.add(Restrictions.eq("subject", name));
		RentSellDTO dto = (RentSellDTO) criteria.uniqueResult();
		log.info("RentDAOImpl FindByLogin method End");
		return dto;
	}
	
	@Override
	public RentSellDTO findByAllocateAndType(AllocateDTO alDto, String type) {
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(RentSellDTO.class);
		criteria.add(Restrictions.eq("allocate", alDto));
		criteria.add(Restrictions.eq("type", type));
		RentSellDTO dto = (RentSellDTO) criteria.uniqueResult();
		return dto;
	}

	@Override
	public void update(RentSellDTO dto) {
		log.info("RentDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		session.merge(dto);
		log.info("RentDAOImpl update method End");
	}

	@Override
	public List<RentSellDTO> list() {
		return list(0, 0);
	}

	@Override
	public List<RentSellDTO> list(int pageNo, int pageSize) {
		log.info("RentDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<RentSellDTO> query = session.createQuery("from RentSellDTO", RentSellDTO.class);
		List<RentSellDTO> list = query.getResultList();
		log.info("RentDAOImpl List method End");
		return list;
	}

	@Override
	public List<RentSellDTO> search(RentSellDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List<RentSellDTO> search(RentSellDTO dto, int pageNo, int pageSize) {
		log.info("RentDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		StringBuffer hql = new StringBuffer("from RentSellDTO as u where 1=1 ");
		if (dto != null) {
			if (dto.getId() > 0) {
				hql.append("and u.id = " + dto.getId());
			}
			if (dto.getSocietyId() > 0) {
				hql.append("and u.societyId = " + dto.getSocietyId());
			}
			if (dto.getType() != null && dto.getType().length() > 0) {
				hql.append("and u.type like '%" + dto.getType() + "%'");
			}
			
		}
		Query<RentSellDTO> query = session.createQuery(hql.toString(), RentSellDTO.class);
		if (pageNo > 0) {
			pageNo = (pageNo - 1) * pageSize;
			query.setFirstResult(pageNo);
			query.setMaxResults(pageSize);
		}
		List<RentSellDTO> list = query.getResultList();
		log.info("RentDAOImpl Search method End");
		return list;
	}

	

	/*
	 * @Override public Blob getImageById(long id) throws SerialException,
	 * SQLException { Session session=entityManager.unwrap(Session.class); RentDTO
	 * person = (RentDTO) session.get(RentDTO.class, id); byte[] blob =
	 * person.getImage(); Blob bBlob= new SerialBlob(blob); return bBlob; }
	 */
	

}
