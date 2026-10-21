package in.com.ehousing.service;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.com.ehousing.dao.AllocateDAOInt;
import in.com.ehousing.dao.HouseDAOInt;
import in.com.ehousing.dao.SocietyDAOInt;
import in.com.ehousing.dto.AllocateDTO;
import in.com.ehousing.exception.DuplicateRecordException;


@Service
public class ALlocateServiceImpl implements AllocateServiceInt {

	private static Logger log=Logger.getLogger(ALlocateServiceImpl.class.getName());
	
	@Autowired
	private AllocateDAOInt dao;
	
	@Autowired
	private HouseDAOInt houseDao;
	
	@Autowired
	private SocietyDAOInt societyDao;
	
	@Override
	@Transactional
	public long add(AllocateDTO dto) throws DuplicateRecordException {
		log.info("AllocateServiceImpl Add method start");
		AllocateDTO existDTO=dao.findByEmail(dto.getEmail());
		if(existDTO !=null)
			throw new DuplicateRecordException("Member Already Exist");
		dto.setHouse(houseDao.findBypk(dto.getHouseId()));
		dto.setSociety(societyDao.findBypk(dto.getSocietyId()));
		long pk=dao.add(dto);
		log.info("AllocateServiceImpl Add method end");
		return pk;
	}

	@Override
	@Transactional
	public void delete(AllocateDTO dto) {
		log.info("AllocateServiceImpl Delete method start");
		dao.delete(dto);
		log.info("AllocateServiceImpl Delete method end");
		
	}

	@Override
	@Transactional
	public AllocateDTO findBypk(long pk) {
		log.info("AllocateServiceImpl findBypk method start");
		AllocateDTO dto=dao.findBypk(pk);
		log.info("AllocateServiceImpl findBypk method end");
		return dto;
	}

	@Override
	@Transactional
	public AllocateDTO findByEmail(String email) {
		log.info("AllocateServiceImpl findByAllocateName method start");
		AllocateDTO dto=dao.findByEmail(email);
		log.info("AllocateServiceImpl findByAllocateName method end");
		return dto;
	}
	
	@Override
	@Transactional
	public AllocateDTO findByUserName(String userName) {
		log.info("AllocateServiceImpl findByUserName method start");
		AllocateDTO dto=dao.findByUserName(userName);
		log.info("AllocateServiceImpl findByUserName method end");
		return dto;
	}


	@Override
	@Transactional
	public void update(AllocateDTO dto) throws DuplicateRecordException {
		log.info("AllocateServiceImpl update method start");
		AllocateDTO existDTO=dao.findByEmail(dto.getEmail());
		if(existDTO !=null && dto.getId()!=existDTO.getId())
			throw new DuplicateRecordException("Member is Already Exist");
		dto.setHouse(houseDao.findBypk(dto.getHouseId()));
		dto.setSociety(societyDao.findBypk(dto.getSocietyId()));
		dao.update(dto);
		log.info("AllocateServiceImpl update method end");
	}

	@Override
	@Transactional
	public List<AllocateDTO> list() {
		log.info("AllocateServiceImpl list method start");
		List<AllocateDTO> list=dao.list();
		log.info("AllocateServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<AllocateDTO> list(int pageNo, int pageSize) {
		log.info("AllocateServiceImpl list method start");
		List<AllocateDTO> list=dao.list(pageNo, pageSize);
		log.info("AllocateServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<AllocateDTO> search(AllocateDTO dto) {
		log.info("AllocateServiceImpl search method start");
		List<AllocateDTO> list=dao.search(dto);
		log.info("AllocateServiceImpl search method end");
		return list;
	}

	@Override
	@Transactional
	public List<AllocateDTO> search(AllocateDTO dto, int pageNo, int pageSize) {
		log.info("AllocateServiceImpl search method start");
		List<AllocateDTO> list=dao.search(dto, pageNo, pageSize);
		log.info("AllocateServiceImpl search method end");
		return list;
	}
	
	@Override
	@Transactional
	public Blob getImageById(long id) throws SerialException, SQLException {
		return dao.getImageById(id);
	}

	
}
