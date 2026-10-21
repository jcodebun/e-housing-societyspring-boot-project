package in.com.ehousing.dao;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import in.com.ehousing.dto.AllocateDTO;
import in.com.ehousing.dto.RentSellDTO;


public interface RentSellDAOInt {

	public long add(RentSellDTO dto);
	
	public void delete(RentSellDTO dto);
	
	public RentSellDTO findBypk(long pk);
	
	public RentSellDTO findByName(String name);
	
	public RentSellDTO findByAllocateAndType(AllocateDTO alDto,String type);
	
	public void update(RentSellDTO dto);
	
	public List<RentSellDTO> list();
	
	public List<RentSellDTO>list(int pageNo,int pageSize);
	
	public List<RentSellDTO> search(RentSellDTO dto);
	
	public List<RentSellDTO> search(RentSellDTO dto,int pageNo,int pageSize);
	
	/* public Blob getImageById(long id) throws SerialException, SQLException; */
	
}
