package in.com.ehousing.service;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import in.com.ehousing.dao.RentSellDAOInt;
import in.com.ehousing.dto.AllocateDTO;
import in.com.ehousing.dto.RentSellDTO;
import in.com.ehousing.exception.DuplicateRecordException;


@Service
public class RentSellServiceImpl implements RentSellServiceInt {

	private static Logger log=Logger.getLogger(RentSellServiceImpl.class.getName());
	
	@Autowired
	private RentSellDAOInt dao;
	
	
	@Override
	@Transactional
	public long add(RentSellDTO dto) throws DuplicateRecordException {
		log.info("RentServiceImpl Add method start");
		long pk=dao.add(dto);
		log.info("RentServiceImpl Add method end");
		return pk;
	}

	@Override
	@Transactional
	public void delete(RentSellDTO dto) {
		log.info("RentServiceImpl Delete method start");
		dao.delete(dto);
		log.info("RentServiceImpl Delete method end");
		
	}

	@Override
	@Transactional
	public RentSellDTO findBypk(long pk) {
		log.info("RentServiceImpl findBypk method start");
		RentSellDTO dto=dao.findBypk(pk);
		log.info("RentServiceImpl findBypk method end");
		return dto;
	}

	@Override
	@Transactional
	public RentSellDTO findByRentName(String name) {
		log.info("RentServiceImpl findByRentName method start");
		RentSellDTO dto=dao.findByName(name);
		log.info("RentServiceImpl findByRentName method end");
		return dto;
	}
	
	@Override
	@Transactional
	public RentSellDTO findByAllocateAndType(AllocateDTO alDto, String type) {
		log.info("RentServiceImpl findByAllocateAndType method start");
		RentSellDTO dto=dao.findByAllocateAndType(alDto, type);
		log.info("RentServiceImpl findByAllocateAndType method end");
		return dto;
	}

	@Override
	@Transactional
	public void update(RentSellDTO dto) throws DuplicateRecordException {
		log.info("RentServiceImpl update method start");
		dao.update(dto);
		log.info("RentServiceImpl update method end");
	}

	@Override
	@Transactional
	public List<RentSellDTO> list() {
		log.info("RentServiceImpl list method start");
		List<RentSellDTO> list=dao.list();
		log.info("RentServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<RentSellDTO> list(int pageNo, int pageSize) {
		log.info("RentServiceImpl list method start");
		List<RentSellDTO> list=dao.list(pageNo, pageSize);
		log.info("RentServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<RentSellDTO> search(RentSellDTO dto) {
		log.info("RentServiceImpl search method start");
		List<RentSellDTO> list=dao.search(dto);
		log.info("RentServiceImpl search method end");
		return list;
	}

	@Override
	@Transactional
	public List<RentSellDTO> search(RentSellDTO dto, int pageNo, int pageSize) {
		log.info("RentServiceImpl search method start");
		List<RentSellDTO> list=dao.search(dto, pageNo, pageSize);
		log.info("RentServiceImpl search method end");
		return list;
	}

	
	
	/*
	 * @Override
	 * 
	 * @Transactional public Blob getImageById(long id) throws SerialException,
	 * SQLException { return dao.getImageById(id); }
	 */

}
