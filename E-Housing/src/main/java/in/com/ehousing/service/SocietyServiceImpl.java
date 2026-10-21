package in.com.ehousing.service;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.com.ehousing.dao.SocietyDAOInt;
import in.com.ehousing.dto.SocietyDTO;
import in.com.ehousing.exception.DuplicateRecordException;


@Service
public class SocietyServiceImpl implements SocietyServiceInt {

	private static Logger log=Logger.getLogger(SocietyServiceImpl.class.getName());
	
	@Autowired
	private SocietyDAOInt dao;
	
	@Autowired
	private JavaMailSenderImpl mailSender;
	
	@Override
	@Transactional
	public long add(SocietyDTO dto) throws DuplicateRecordException {
		log.info("SocietyServiceImpl Add method start");
		SocietyDTO existDTO=dao.findByName(dto.getName());
		if(existDTO !=null)
			throw new DuplicateRecordException("SocietyName Already Exist");
		long pk=dao.add(dto);
		log.info("SocietyServiceImpl Add method end");
		return pk;
	}

	@Override
	@Transactional
	public void delete(SocietyDTO dto) {
		log.info("SocietyServiceImpl Delete method start");
		dao.delete(dto);
		log.info("SocietyServiceImpl Delete method end");
		
	}

	@Override
	@Transactional
	public SocietyDTO findBypk(long pk) {
		log.info("SocietyServiceImpl findBypk method start");
		SocietyDTO dto=dao.findBypk(pk);
		log.info("SocietyServiceImpl findBypk method end");
		return dto;
	}

	@Override
	@Transactional
	public SocietyDTO findBySocietyName(String name) {
		log.info("SocietyServiceImpl findBySocietyName method start");
		SocietyDTO dto=dao.findByName(name);
		log.info("SocietyServiceImpl findBySocietyName method end");
		return dto;
	}

	@Override
	@Transactional
	public void update(SocietyDTO dto) throws DuplicateRecordException {
		log.info("SocietyServiceImpl update method start");
		SocietyDTO existDTO=dao.findByName(dto.getName());
		if(existDTO !=null && dto.getId()!=existDTO.getId())
			throw new DuplicateRecordException("Society Name Already Exist");
		dao.update(dto);
		log.info("SocietyServiceImpl update method end");
	}

	@Override
	@Transactional
	public List<SocietyDTO> list() {
		log.info("SocietyServiceImpl list method start");
		List<SocietyDTO> list=dao.list();
		log.info("SocietyServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<SocietyDTO> list(int pageNo, int pageSize) {
		log.info("SocietyServiceImpl list method start");
		List<SocietyDTO> list=dao.list(pageNo, pageSize);
		log.info("SocietyServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<SocietyDTO> search(SocietyDTO dto) {
		log.info("SocietyServiceImpl search method start");
		List<SocietyDTO> list=dao.search(dto);
		log.info("SocietyServiceImpl search method end");
		return list;
	}

	@Override
	@Transactional
	public List<SocietyDTO> search(SocietyDTO dto, int pageNo, int pageSize) {
		log.info("SocietyServiceImpl search method start");
		List<SocietyDTO> list=dao.search(dto, pageNo, pageSize);
		log.info("SocietyServiceImpl search method end");
		return list;
	}
	
	@Override
	@Transactional
	public Blob getImageById(long id) throws SerialException, SQLException {
		return dao.getImageById(id);
	}

}
