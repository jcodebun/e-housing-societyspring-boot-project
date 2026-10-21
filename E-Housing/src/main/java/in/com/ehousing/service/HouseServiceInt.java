package in.com.ehousing.service;

import java.util.List;


import in.com.ehousing.dto.HouseDTO;
import in.com.ehousing.exception.DuplicateRecordException;



public interface HouseServiceInt {

	public long add(HouseDTO dto) throws DuplicateRecordException;

	public void delete(HouseDTO dto);

	public HouseDTO findBypk(long pk);

	public HouseDTO findByHouseName(String name);

	public void update(HouseDTO dto) throws DuplicateRecordException;

	public List<HouseDTO> list();

	public List<HouseDTO> list(int pageNo, int pageSize);

	public List<HouseDTO> search(HouseDTO dto);

	public List<HouseDTO> search(HouseDTO dto, int pageNo, int pageSize);
	
	/* public Blob getImageById(long id) throws SerialException, SQLException; */
	

	

}
