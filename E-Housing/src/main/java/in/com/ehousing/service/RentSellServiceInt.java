package in.com.ehousing.service;

import java.util.List;

import in.com.ehousing.dto.AllocateDTO;
import in.com.ehousing.dto.RentSellDTO;
import in.com.ehousing.exception.DuplicateRecordException;



public interface RentSellServiceInt {

	public long add(RentSellDTO dto) throws DuplicateRecordException;

	public void delete(RentSellDTO dto);

	public RentSellDTO findBypk(long pk);

	public RentSellDTO findByRentName(String name);
	
	public RentSellDTO findByAllocateAndType(AllocateDTO alDto,String type);

	public void update(RentSellDTO dto) throws DuplicateRecordException;

	public List<RentSellDTO> list();

	public List<RentSellDTO> list(int pageNo, int pageSize);

	public List<RentSellDTO> search(RentSellDTO dto);

	public List<RentSellDTO> search(RentSellDTO dto, int pageNo, int pageSize);
	
	/* public Blob getImageById(long id) throws SerialException, SQLException; */
	

	

}
