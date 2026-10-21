package in.com.ehousing.service;

import java.util.List;
import java.util.logging.Logger;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.com.ehousing.dao.HouseDAOInt;
import in.com.ehousing.dao.SocietyDAOInt;
import in.com.ehousing.dto.HouseDTO;
import in.com.ehousing.dto.SocietyDTO;
import in.com.ehousing.exception.DuplicateRecordException;


@Service
public class HouseServiceImpl implements HouseServiceInt {

	private static Logger log=Logger.getLogger(HouseServiceImpl.class.getName());
	
	@Autowired
	private HouseDAOInt dao;
	
	@Autowired
	private SocietyDAOInt societyDao;
	
	@Autowired
	private JavaMailSenderImpl mailSender;
	
	@Override
	@Transactional
	public long add(HouseDTO dto) throws DuplicateRecordException {
		log.info("HouseServiceImpl Add method start");
		HouseDTO existDTO=dao.findByName(dto.getName());
		if(existDTO !=null)
			throw new DuplicateRecordException("HouseName Already Exist");
		dto.setSociety(societyDao.findBypk(dto.getSocietyId()));
		long pk=dao.add(dto);
		log.info("HouseServiceImpl Add method end");
		return pk;
	}

	@Override
	@Transactional
	public void delete(HouseDTO dto) {
		log.info("HouseServiceImpl Delete method start");
		dao.delete(dto);
		log.info("HouseServiceImpl Delete method end");
		
	}

	@Override
	@Transactional
	public HouseDTO findBypk(long pk) {
		log.info("HouseServiceImpl findBypk method start");
		HouseDTO dto=dao.findBypk(pk);
		log.info("HouseServiceImpl findBypk method end");
		return dto;
	}

	@Override
	@Transactional
	public HouseDTO findByHouseName(String name) {
		log.info("HouseServiceImpl findByHouseName method start");
		HouseDTO dto=dao.findByName(name);
		log.info("HouseServiceImpl findByHouseName method end");
		return dto;
	}

	@Override
	@Transactional
	public void update(HouseDTO dto) throws DuplicateRecordException {
		log.info("HouseServiceImpl update method start");
		HouseDTO existDTO=dao.findByName(dto.getName());
		if(existDTO !=null && dto.getId()!=existDTO.getId())
			throw new DuplicateRecordException("House Name Already Exist");
		dto.setSociety(societyDao.findBypk(dto.getSocietyId()));
		dao.update(dto);
		log.info("HouseServiceImpl update method end");
	}

	@Override
	@Transactional
	public List<HouseDTO> list() {
		log.info("HouseServiceImpl list method start");
		List<HouseDTO> list=dao.list();
		log.info("HouseServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<HouseDTO> list(int pageNo, int pageSize) {
		log.info("HouseServiceImpl list method start");
		List<HouseDTO> list=dao.list(pageNo, pageSize);
		log.info("HouseServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<HouseDTO> search(HouseDTO dto) {
		log.info("HouseServiceImpl search method start");
		List<HouseDTO> list=dao.search(dto);
		log.info("HouseServiceImpl search method end");
		return list;
	}

	@Override
	@Transactional
	public List<HouseDTO> search(HouseDTO dto, int pageNo, int pageSize) {
		log.info("HouseServiceImpl search method start");
		List<HouseDTO> list=dao.search(dto, pageNo, pageSize);
		log.info("HouseServiceImpl search method end");
		return list;
	}
	
	/*
	 * @Override
	 * 
	 * @Transactional public Blob getImageById(long id) throws SerialException,
	 * SQLException { return dao.getImageById(id); }
	 */

}
