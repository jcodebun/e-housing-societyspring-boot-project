package in.com.ehousing.service;

import java.util.List;


import in.com.ehousing.dto.ComplainDTO;
import in.com.ehousing.exception.DuplicateRecordException;



public interface ComplainServiceInt {

	public long add(ComplainDTO dto) throws DuplicateRecordException;

	public void delete(ComplainDTO dto);

	public ComplainDTO findBypk(long pk);

	public ComplainDTO findByComplainName(String name);

	public void update(ComplainDTO dto) throws DuplicateRecordException;

	public List<ComplainDTO> list();

	public List<ComplainDTO> list(int pageNo, int pageSize);

	public List<ComplainDTO> search(ComplainDTO dto);

	public List<ComplainDTO> search(ComplainDTO dto, int pageNo, int pageSize);
	
	/* public Blob getImageById(long id) throws SerialException, SQLException; */
	

	

}
