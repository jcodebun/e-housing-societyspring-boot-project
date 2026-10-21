package in.com.ehousing.service;

import java.util.List;
import java.util.logging.Logger;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.com.ehousing.dao.ComplainDAOInt;
import in.com.ehousing.dto.ComplainDTO;
import in.com.ehousing.exception.DuplicateRecordException;


@Service
public class ComplainServiceImpl implements ComplainServiceInt {

	private static Logger log=Logger.getLogger(ComplainServiceImpl.class.getName());
	
	@Autowired
	private ComplainDAOInt dao;
	
	
	@Override
	@Transactional
	public long add(ComplainDTO dto) throws DuplicateRecordException {
		log.info("ComplainServiceImpl Add method start");
		ComplainDTO existDTO=dao.findByName(dto.getSubject());
		if(existDTO !=null)
			throw new DuplicateRecordException("Complain Already Exist");
		long pk=dao.add(dto);
		log.info("ComplainServiceImpl Add method end");
		return pk;
	}

	@Override
	@Transactional
	public void delete(ComplainDTO dto) {
		log.info("ComplainServiceImpl Delete method start");
		dao.delete(dto);
		log.info("ComplainServiceImpl Delete method end");
		
	}

	@Override
	@Transactional
	public ComplainDTO findBypk(long pk) {
		log.info("ComplainServiceImpl findBypk method start");
		ComplainDTO dto=dao.findBypk(pk);
		log.info("ComplainServiceImpl findBypk method end");
		return dto;
	}

	@Override
	@Transactional
	public ComplainDTO findByComplainName(String name) {
		log.info("ComplainServiceImpl findByComplainName method start");
		ComplainDTO dto=dao.findByName(name);
		log.info("ComplainServiceImpl findByComplainName method end");
		return dto;
	}

	@Override
	@Transactional
	public void update(ComplainDTO dto) throws DuplicateRecordException {
		log.info("ComplainServiceImpl update method start");
		ComplainDTO existDTO=dao.findByName(dto.getSubject());
		if(existDTO !=null && dto.getId()!=existDTO.getId())
			throw new DuplicateRecordException("Complain  Already Exist");
		dao.update(dto);
		log.info("ComplainServiceImpl update method end");
	}

	@Override
	@Transactional
	public List<ComplainDTO> list() {
		log.info("ComplainServiceImpl list method start");
		List<ComplainDTO> list=dao.list();
		log.info("ComplainServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<ComplainDTO> list(int pageNo, int pageSize) {
		log.info("ComplainServiceImpl list method start");
		List<ComplainDTO> list=dao.list(pageNo, pageSize);
		log.info("ComplainServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<ComplainDTO> search(ComplainDTO dto) {
		log.info("ComplainServiceImpl search method start");
		List<ComplainDTO> list=dao.search(dto);
		log.info("ComplainServiceImpl search method end");
		return list;
	}

	@Override
	@Transactional
	public List<ComplainDTO> search(ComplainDTO dto, int pageNo, int pageSize) {
		log.info("ComplainServiceImpl search method start");
		List<ComplainDTO> list=dao.search(dto, pageNo, pageSize);
		log.info("ComplainServiceImpl search method end");
		return list;
	}
	
	/*
	 * @Override
	 * 
	 * @Transactional public Blob getImageById(long id) throws SerialException,
	 * SQLException { return dao.getImageById(id); }
	 */

}
