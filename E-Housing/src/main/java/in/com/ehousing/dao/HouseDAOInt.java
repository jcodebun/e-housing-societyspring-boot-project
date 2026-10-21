package in.com.ehousing.dao;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import in.com.ehousing.dto.HouseDTO;


public interface HouseDAOInt {

	public long add(HouseDTO dto);
	
	public void delete(HouseDTO dto);
	
	public HouseDTO findBypk(long pk);
	
	public HouseDTO findByName(String name);
	
	public void update(HouseDTO dto);
	
	public List<HouseDTO> list();
	
	public List<HouseDTO>list(int pageNo,int pageSize);
	
	public List<HouseDTO> search(HouseDTO dto);
	
	public List<HouseDTO> search(HouseDTO dto,int pageNo,int pageSize);
	
	/* public Blob getImageById(long id) throws SerialException, SQLException; */
	
}
