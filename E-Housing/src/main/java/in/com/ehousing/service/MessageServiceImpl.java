package in.com.ehousing.service;

import java.util.List;
import java.util.logging.Logger;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.com.ehousing.dao.MessageDAOInt;
import in.com.ehousing.dao.SocietyDAOInt;
import in.com.ehousing.dto.MessageDTO;
import in.com.ehousing.dto.SocietyDTO;
import in.com.ehousing.exception.DuplicateRecordException;


@Service
public class MessageServiceImpl implements MessageServiceInt {

	private static Logger log=Logger.getLogger(MessageServiceImpl.class.getName());
	
	@Autowired
	private MessageDAOInt dao;
	
	
	@Override
	@Transactional
	public long add(MessageDTO dto) throws DuplicateRecordException {
		log.info("MessageServiceImpl Add method start");
		
		long pk=dao.add(dto);
		log.info("MessageServiceImpl Add method end");
		return pk;
	}

	@Override
	@Transactional
	public void delete(MessageDTO dto) {
		log.info("MessageServiceImpl Delete method start");
		dao.delete(dto);
		log.info("MessageServiceImpl Delete method end");
		
	}

	@Override
	@Transactional
	public MessageDTO findBypk(long pk) {
		log.info("MessageServiceImpl findBypk method start");
		MessageDTO dto=dao.findBypk(pk);
		log.info("MessageServiceImpl findBypk method end");
		return dto;
	}

	@Override
	@Transactional
	public MessageDTO findByMessageName(String name) {
		log.info("MessageServiceImpl findByMessageName method start");
		MessageDTO dto=dao.findByName(name);
		log.info("MessageServiceImpl findByMessageName method end");
		return dto;
	}

	@Override
	@Transactional
	public void update(MessageDTO dto) throws DuplicateRecordException {
		log.info("MessageServiceImpl update method start");
		dao.update(dto);
		log.info("MessageServiceImpl update method end");
	}

	@Override
	@Transactional
	public List<MessageDTO> list() {
		log.info("MessageServiceImpl list method start");
		List<MessageDTO> list=dao.list();
		log.info("MessageServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<MessageDTO> list(int pageNo, int pageSize) {
		log.info("MessageServiceImpl list method start");
		List<MessageDTO> list=dao.list(pageNo, pageSize);
		log.info("MessageServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<MessageDTO> search(MessageDTO dto) {
		log.info("MessageServiceImpl search method start");
		List<MessageDTO> list=dao.search(dto);
		log.info("MessageServiceImpl search method end");
		return list;
	}

	@Override
	@Transactional
	public List<MessageDTO> search(MessageDTO dto, int pageNo, int pageSize) {
		log.info("MessageServiceImpl search method start");
		List<MessageDTO> list=dao.search(dto, pageNo, pageSize);
		log.info("MessageServiceImpl search method end");
		return list;
	}
	
	/*
	 * @Override
	 * 
	 * @Transactional public Blob getImageById(long id) throws SerialException,
	 * SQLException { return dao.getImageById(id); }
	 */

}
